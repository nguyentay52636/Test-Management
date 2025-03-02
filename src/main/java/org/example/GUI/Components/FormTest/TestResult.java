package org.example.GUI.Components.FormTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestResult extends JPanel {

    private JPanel contentPanel;

    public TestResult(JPanel contentPanel, int correctAnswers, int totalQuestions, int score, String timeTaken) {
        this.contentPanel = contentPanel;
        initComponents(correctAnswers, totalQuestions, score, timeTaken);
    }

    private void initComponents(int correctAnswers, int totalQuestions, int score, String timeTaken) {
        setLayout(null);
        setBackground(new Color(45, 50, 70)); // Màu nền tối hơn một chút

        // Font chung
        Font textFont = new Font("Segoe UI", Font.BOLD, 22);

        // Số câu đúng
        JLabel correctLabel = new JLabel("Số câu đúng: " + correctAnswers + "/" + totalQuestions);
        correctLabel.setFont(textFont);
        correctLabel.setForeground(Color.WHITE);
        correctLabel.setBounds(50, 50, 300, 30);
        add(correctLabel);

        // Kết quả điểm
        JLabel scoreLabel = new JLabel("Kết quả: " + score + "/10");
        scoreLabel.setFont(textFont);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(50, 90, 300, 30);
        add(scoreLabel);

        // Thời gian làm bài
        JLabel timeLabel = new JLabel("Thời gian: " + timeTaken);
        timeLabel.setFont(textFont);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(50, 130, 300, 30);
        add(timeLabel);

        // Tiêu đề "Inbox"
        JLabel inboxLabel = new JLabel("Inbox", SwingConstants.CENTER);
        inboxLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        inboxLabel.setForeground(Color.WHITE);
        inboxLabel.setBounds(0, 200, getWidth(), 30);
        add(inboxLabel);

        // Nút Trở lại
        JButton backButton = new JButton("Trở lại");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        backButton.setBounds(200, 270, 150, 50);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        add(backButton);

        // Sự kiện nút Trở lại để quay về ExamForm
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contentPanel != null) {
                    contentPanel.removeAll();
                    contentPanel.setLayout(new BorderLayout());

                    // Chuyển về giao diện ExamForm
                    TestForm examForm = new TestForm(contentPanel);
                    contentPanel.add(examForm, BorderLayout.CENTER);

                    contentPanel.revalidate();
                    contentPanel.repaint();
                }
            }
        });
    }
}
