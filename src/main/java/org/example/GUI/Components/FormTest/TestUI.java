package org.example.GUI.Components.FormTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.example.BUS.TestBUS;
import org.example.DAO.TestDAO;
import org.example.DTO.TestDTO;

public class TestUI extends JPanel {
    private JPanel contentPanel;
    private JComboBox<String> testComboBox;
    private JLabel timeLabel, questionLabel;
    private List<TestDTO> testList;
    private TestBUS testBUS;

    public TestUI(JPanel contentPanel, int tpID) {
        this.contentPanel = contentPanel;
        initComponents(tpID);
        this.testBUS = new TestBUS();
        testList = testBUS.getTestsByTopicID(tpID);
    }

    private void initComponents(int tpID) {
        setLayout(null);
        setBackground(new Color(50, 50, 60));

        JLabel titleLabel = new JLabel("Chọn Bài Kiểm Tra", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 20, 400, 40);
        add(titleLabel);

        JLabel testLabel = new JLabel("Chọn bài kiểm tra:");
        testLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        testLabel.setForeground(Color.WHITE);
        testLabel.setBounds(150, 80, 200, 30);
        add(testLabel);

        // Lấy danh sách bài kiểm tra từ database
        TestDAO testDAO = new TestDAO();
        testList = testDAO.getTestsByTopicID(tpID);

        testComboBox = new JComboBox<>();
        testComboBox.setFont(new Font("Segoe UI", Font.BOLD, 20));
        testComboBox.setBounds(370, 80, 250, 35);
        add(testComboBox);

        for (TestDTO test : testList) {
            testComboBox.addItem(test.getTitle());
        }

        testComboBox.addActionListener(e -> updateTestInfo());

        // Hiển thị thông tin bài kiểm tra
        timeLabel = createLabel("Thời gian: ", 150, 130);
        questionLabel = createLabel("Số lượng câu hỏi: ", 150, 170);

        JButton exitButton = new JButton("Thoát");
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        exitButton.setBounds(200, 280, 120, 50);
        add(exitButton);
        exitButton.addActionListener(e -> backToTestForm());

        JButton startButton = new JButton("Làm bài");
        startButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        startButton.setBounds(350, 280, 150, 50);
        startButton.setBackground(new Color(255, 0, 0));
        startButton.setForeground(Color.WHITE);
        add(startButton);

        startButton.addActionListener(e -> startTest());

        // Cập nhật thông tin bài kiểm tra đầu tiên (nếu có)
        if (!testList.isEmpty()) {
            updateTestInfo();
        }
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 22));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, 500, 30);
        add(label);
        return label;
    }

    private void updateTestInfo() {
        int selectedIndex = testComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            TestDTO selectedTest = testList.get(selectedIndex);
            timeLabel.setText("Thời gian: " + selectedTest.getTestTime() + " phút");
            int totalQuestions = selectedTest.getNumberEasy() + selectedTest.getNumberMedium() + selectedTest.getNumberDiff();
            questionLabel.setText("Số lượng câu hỏi: " + totalQuestions);
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
            // Chuyển sang giao diện làm bài kiểm tra nếu cần
            if (contentPanel != null) {
                QuizUI quizUI = new QuizUI(testCode);                
                contentPanel.removeAll();
                contentPanel.setLayout(new BorderLayout());
                contentPanel.add(quizUI, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
                    }
    }
}
