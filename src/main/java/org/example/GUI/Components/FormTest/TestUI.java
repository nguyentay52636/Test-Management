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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.example.BUS.TestBUS;
import org.example.DAO.TestDAO;
import org.example.DTO.TestDTO;
import org.example.DTO.Test_structureDTO;

public class TestUI extends JPanel {
    private JPanel contentPanel;
    private JComboBox<String> testComboBox;
    private JLabel timeLabel, questionLabel;
    private List<TestDTO> testList;
    private List<Test_structureDTO> testStructure ; 
    private TestBUS testBUS;

    public TestUI(JPanel contentPanel, int tpID) {
        this.contentPanel = contentPanel;
        this.testBUS = new TestBUS();
        testList = testBUS.getTestsByTopicID(tpID);

        initComponents(tpID);
    }

    private void initComponents(int tpID) {
        setLayout(new BorderLayout(0, 20));
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Header Panel with Gradient
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(52, 152, 219), 0, getHeight(), new Color(41, 128, 185)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        add(headerPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Chọn Bài Kiểm Tra");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Main Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        add(contentPanel, BorderLayout.CENTER);

        // Test Selection
        JLabel testLabel = new JLabel("Chọn bài kiểm tra:");
        testLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        testLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(testLabel, gbc);

        testComboBox = new JComboBox<>();
        testComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        testComboBox.setBackground(Color.WHITE);
        testComboBox.setForeground(new Color(44, 62, 80));
        testComboBox.setPreferredSize(new Dimension(300, 40));
        testComboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(testComboBox, gbc);

        // Populate ComboBox
        TestDAO testDAO = new TestDAO();
        testList = testDAO.getTestsByTopicID(tpID);
        for (TestDTO test : testList) {
            testComboBox.addItem(test.getTitle());
        }
        testComboBox.addActionListener(e -> updateTestInfo());

        // Test Info Labels
        timeLabel = createStyledLabel("Thời gian: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        contentPanel.add(timeLabel, gbc);

        questionLabel = createStyledLabel("Số lượng câu hỏi: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(questionLabel, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contentPanel.add(buttonPanel, gbc);

        JButton exitButton = createStyledButton("Thoát", new Color(108, 117, 125), Color.WHITE);
        exitButton.addActionListener(e -> backToTestForm());
        buttonPanel.add(exitButton);

        JButton startButton = createStyledButton("Làm bài", new Color(231, 76, 60), Color.WHITE);
        startButton.addActionListener(e -> startTest());
        buttonPanel.add(startButton);

        if (!testList.isEmpty()) {
            updateTestInfo();
        }
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label.setForeground(new Color(44, 62, 80));
        return label;
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(120, 45));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { button.setBackground(bgColor.brighter()); }
            @Override
            public void mouseExited(MouseEvent e) { button.setBackground(bgColor); }
            @Override
            public void mousePressed(MouseEvent e) { button.setBackground(bgColor.darker()); }
            @Override
            public void mouseReleased(MouseEvent e) { button.setBackground(bgColor.brighter()); }
        });

        return button;
    }

    private void updateTestInfo() {
        int selectedIndex = testComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            TestDTO selectedTest = testList.get(selectedIndex);
            Test_structureDTO testStructure = testBUS.getTestStructureByTestCode(selectedTest.getTestCode());
    
            timeLabel.setText("Thời gian: " + selectedTest.getTestTime() + " phút");
    
            if (testStructure != null) {
                int totalQuestions = testStructure.getNumberEasy() 
                                   + testStructure.getNumberMedium() 
                                   + testStructure.getNumberDiff();
                questionLabel.setText("Số lượng câu hỏi: " + totalQuestions);
            } else {
                questionLabel.setText("Không có dữ liệu số câu hỏi!");
            }
        } else {
            questionLabel.setText("Vui lòng chọn một bài kiểm tra!");
        }
    }
    
    

    private void backToTestForm() {
        if (contentPanel != null) {
            contentPanel.removeAll();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(new TestForm(contentPanel), BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    private void startTest() {
        int selectedIndex = testComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            TestDTO selectedTest = testList.get(selectedIndex);
            String testCode = selectedTest.getTestCode();

            JOptionPane.showMessageDialog(this, "Bắt đầu bài kiểm tra: " + selectedTest.getTitle());
            if (contentPanel != null) {
                QuizUI quizUI = new QuizUI(testCode, contentPanel); // Pass contentPanel to QuizUI
                contentPanel.removeAll();
                contentPanel.setLayout(new BorderLayout());
                contentPanel.add(quizUI, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        }
    }
}