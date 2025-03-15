package org.example.GUI.Components.FormTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import org.example.BUS.TestBUS;
import org.example.BUS.TopicBUS;
import org.example.DTO.SessionManager;
import org.example.DTO.TestDTO;
import org.example.DTO.TopicsDTO;

public class FormSelectionTest extends JPanel {
    private JPanel contentPanel;
    private JComboBox<TopicsDTO> topicComboBox;
    private JPanel testListPanel;
    private JLabel loadingLabel;

    public FormSelectionTest(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 247, 250));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        // Header Panel with Gradient
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(66, 139, 202), 0, getHeight(),
                        new Color(48, 99, 152));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        add(headerPanel, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("Thi Trực Tuyến");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle, BorderLayout.WEST);

        JLabel lblUser = new JLabel("Người dùng: " + SessionManager.getCurrentUser().getUserName());
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUser.setForeground(new Color(230, 240, 255));
        headerPanel.add(lblUser, BorderLayout.EAST);

        // Toolbar Panel
        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        toolbarPanel.setOpaque(false);
        toolbarPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel lblSelectTopic = new JLabel("Chọn chủ đề:");
        lblSelectTopic.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblSelectTopic.setForeground(new Color(50, 50, 50));

        topicComboBox = new JComboBox<>();
        topicComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topicComboBox.setPreferredSize(new Dimension(350, 40));
        topicComboBox.setBackground(Color.WHITE);
        topicComboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        topicComboBox.setRenderer(new TopicComboBoxRenderer());
        topicComboBox.addActionListener(e -> loadTestsForTopicAsync());

        toolbarPanel.add(lblSelectTopic);
        toolbarPanel.add(topicComboBox);
        add(toolbarPanel, BorderLayout.CENTER);

        // Test List Panel with Loading Indicator
        testListPanel = new JPanel(new GridBagLayout());
        testListPanel.setOpaque(false);
        testListPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        JScrollPane testScrollPane = new JScrollPane(testListPanel);
        testScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true), "Danh sách bài thi",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.PLAIN, 14)));
        testScrollPane.setOpaque(false);
        testScrollPane.getViewport().setOpaque(false);
        testScrollPane.setPreferredSize(new Dimension(0, 400));
        add(testScrollPane, BorderLayout.SOUTH);

        // Loading Indicator
        loadingLabel = new JLabel("Đang tải dữ liệu...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loadingLabel.setForeground(new Color(108, 117, 125));
        testListPanel.add(loadingLabel, new GridBagConstraints());

        // Load topics asynchronously
        loadTopicsAsync();
    }

    private void loadTopicsAsync() {
        SwingWorker<List<TopicsDTO>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<TopicsDTO> doInBackground() {
                TopicBUS topicsBUS = new TopicBUS();
                return topicsBUS.getAllTopics(); // Tải danh sách chủ đề
            }

            @Override
            protected void done() {
                try {
                    List<TopicsDTO> topicsList = get();
                    topicComboBox.removeAllItems();
                    for (TopicsDTO topic : topicsList) {
                        topicComboBox.addItem(topic);
                    }
                    if (!topicsList.isEmpty()) {
                        loadTestsForTopicAsync(); // Tải bài thi cho chủ đề đầu tiên
                    } else {
                        testListPanel.removeAll();
                        JLabel noTopicsLabel = new JLabel("Không có chủ đề nào!", SwingConstants.CENTER);
                        noTopicsLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
                        noTopicsLabel.setForeground(new Color(220, 53, 69));
                        testListPanel.add(noTopicsLabel, new GridBagConstraints());
                        testListPanel.revalidate();
                        testListPanel.repaint();
                    }
                } catch (InterruptedException | ExecutionException e) {
                    JOptionPane.showMessageDialog(FormSelectionTest.this, "Lỗi khi tải danh sách chủ đề!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private void loadTestsForTopicAsync() {
        TopicsDTO selectedTopic = (TopicsDTO) topicComboBox.getSelectedItem();
        if (selectedTopic == null) {
            return;
        }

        // Hiển thị loading indicator
        testListPanel.removeAll();
        testListPanel.add(loadingLabel, new GridBagConstraints());
        testListPanel.revalidate();
        testListPanel.repaint();

        SwingWorker<List<TestDTO>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<TestDTO> doInBackground() {
                TestBUS testBUS = new TestBUS();
                return testBUS.getTestsByTopicID(selectedTopic.getTopicID()); // Tải danh sách bài thi
            }

            @Override
            protected void done() {
                try {
                    List<TestDTO> testList = get();
                    testListPanel.removeAll();

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(10, 10, 10, 10);
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.weightx = 1.0;
                    gbc.weighty = 0.0;

                    if (testList == null || testList.isEmpty()) {
                        JLabel noTestsLabel = new JLabel("Không có bài thi nào cho chủ đề này!", SwingConstants.CENTER);
                        noTestsLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                        noTestsLabel.setForeground(new Color(220, 53, 69));
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        testListPanel.add(noTestsLabel, gbc);
                    } else {
                        int row = 0;
                        for (TestDTO test : testList) {
                            JPanel testPanel = new JPanel(new BorderLayout(10, 0));
                            testPanel.setOpaque(false);
                            testPanel.setBorder(BorderFactory.createCompoundBorder(
                                    BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                                    BorderFactory.createEmptyBorder(10, 15, 10, 15)));
                            testPanel.setMaximumSize(new Dimension(600, 70));

                            JLabel lblTestCode = new JLabel("Mã đề: " + test.getTestCode());
                            lblTestCode.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                            lblTestCode.setForeground(new Color(50, 50, 50));
                            testPanel.add(lblTestCode, BorderLayout.CENTER);

                            JButton btnStart = createStyledButton("Bắt đầu", new Color(46, 204, 113));
                            btnStart.addActionListener(e -> startTest(test));
                            testPanel.add(btnStart, BorderLayout.EAST);

                            gbc.gridx = 0;
                            gbc.gridy = row++;
                            testListPanel.add(testPanel, gbc);
                        }
                    }
                    testListPanel.revalidate();
                    testListPanel.repaint();
                } catch (InterruptedException | ExecutionException e) {
                    JOptionPane.showMessageDialog(FormSelectionTest.this, "Lỗi khi tải danh sách bài thi!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(new EmptyBorder(5, 10, 5, 10));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        return button;
    }

    private void startTest(TestDTO test) {
        if (contentPanel != null) {
            if (test.getTestStructure() == null) {
                JOptionPane.showMessageDialog(this, "Cấu trúc bài thi chưa được khởi tạo!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            TestUI examUI = new TestUI(contentPanel, test); // Truyền test trực tiếp
            contentPanel.removeAll();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(examUI, BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    private class TopicComboBoxRenderer extends javax.swing.DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof TopicsDTO) {
                setText(((TopicsDTO) value).getTpTitle());
            }
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
            if (isSelected) {
                setBackground(new Color(230, 240, 255));
                setForeground(new Color(33, 150, 243));
            } else {
                setBackground(Color.WHITE);
                setForeground(new Color(50, 50, 50));
            }
            return this;
        }
    }

    private int getCurrentUserID() {
        return SessionManager.getCurrentUser().getUserID();
    }
}