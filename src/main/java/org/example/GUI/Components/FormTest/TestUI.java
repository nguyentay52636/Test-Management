package org.example.GUI.Components.FormTest;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.example.BUS.TestBUS;
import org.example.DTO.TestDTO;
import org.example.DTO.Test_structureDTO;

public class TestUI extends JPanel {
    private JPanel contentPanel;
    private JLabel titleLabel, timeLabel, questionLabel, testCodeLabel;
    private TestDTO selectedTest;
    private TestBUS testBUS;

    public TestUI(JPanel contentPanel, TestDTO selectedTest) {
        this.contentPanel = contentPanel;
        this.testBUS = new TestBUS();
        this.selectedTest = selectedTest;

        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(240, 242, 245)); // Màu nền nhẹ nhàng, hiện đại
        setBorder(new EmptyBorder(25, 25, 25, 25)); // Tăng padding tổng thể
        initComponents();
    }

    private void initComponents() {
        // Header Panel with Gradient
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(52, 152, 219), getWidth(), getHeight(),
                        new Color(41, 128, 185)); // Gradient xéo nhẹ
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 100)); // Tăng chiều cao header
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(headerPanel, BorderLayout.NORTH);

        titleLabel = new JLabel(
                "Bài Kiểm Tra: " + (selectedTest != null ? selectedTest.getTitle() : "Không có tiêu đề"));
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28)); // Tăng kích thước font
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBackground(new Color(255, 255, 255)); // Nền trắng cho nội dung
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 223, 230), 1, true), // Viền mềm mại
                new EmptyBorder(20, 20, 20, 20)));
        add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 15, 12, 15); // Khoảng cách đều hơn
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Test Code Label
        testCodeLabel = createStyledLabel(
                "Mã đề: " + (selectedTest != null ? selectedTest.getTestCode() : "Không có mã đề"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(testCodeLabel, gbc);

        // Time Label
        timeLabel = createStyledLabel(
                "Thời gian: " + (selectedTest != null ? selectedTest.getTestTime() + " phút" : "Không có dữ liệu"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(timeLabel, gbc);

        // Question Label
        questionLabel = createStyledLabel("Số lượng câu hỏi: Đang tải...");
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(questionLabel, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton exitButton = createStyledButton("Quay lại", new Color(108, 117, 125));
        exitButton.addActionListener(e -> backToTestForm());
        buttonPanel.add(exitButton);

        JButton startButton = createStyledButton("Bắt đầu", new Color(46, 204, 113));
        startButton.addActionListener(e -> startTest());
        buttonPanel.add(startButton);

        // Load test structure info
        if (selectedTest != null) {
            updateTestInfo();
        } else {
            questionLabel.setText("Số lượng câu hỏi: Không có dữ liệu");
        }
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Font lớn hơn, dễ đọc
        label.setForeground(new Color(44, 62, 80)); // Màu chữ tối, chuyên nghiệp
        label.setBorder(new EmptyBorder(5, 0, 5, 0)); // Padding nhẹ
        return label;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Bo góc mềm hơn
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Font lớn hơn
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(140, 50)); // Nút lớn hơn
        button.setBorder(new EmptyBorder(10, 15, 10, 15));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Con trỏ tay

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

    private void updateTestInfo() {
        if (selectedTest != null) {
            Test_structureDTO testStructure = testBUS.getTestStructureByTestCode(selectedTest.getTestCode());

            if (testStructure != null) {
                int totalQuestions = testStructure.getNumberEasy()
                        + testStructure.getNumberMedium()
                        + testStructure.getNumberDiff();
                questionLabel.setText("Số lượng câu hỏi: " + totalQuestions);
            } else {
                questionLabel.setText("Số lượng câu hỏi: Không có dữ liệu");
            }
        } else {
            timeLabel.setText("Thời gian: Không có dữ liệu");
            questionLabel.setText("Số lượng câu hỏi: Không có dữ liệu");
        }
    }

    private void backToTestForm() {
        if (contentPanel != null) {
            try {
                contentPanel.removeAll();
                contentPanel.setLayout(new BorderLayout());
                FormSelectionTest form = new FormSelectionTest(contentPanel);
                contentPanel.add(form, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
                System.out.println("✅ Đã quay lại FormSelectionTest");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi quay lại: " + e.getMessage(), "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("❌ Lỗi trong backToTestForm: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("❌ contentPanel là null!");
        }
    }

    private void startTest() {
        if (selectedTest != null) {
            String testCode = selectedTest.getTestCode();
            Test_structureDTO testStructure = testBUS.getTestStructureByTestCode(testCode);

            if (testStructure == null) {
                JOptionPane.showMessageDialog(this, "Cấu trúc bài thi chưa được khởi tạo!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Bắt đầu bài kiểm tra: " + selectedTest.getTitle(),
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            if (contentPanel != null) {
                QuizUI quizUI = new QuizUI(testCode, contentPanel);
                contentPanel.removeAll();
                contentPanel.setLayout(new BorderLayout());
                contentPanel.add(quizUI, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không có bài kiểm tra nào được chọn!", "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}