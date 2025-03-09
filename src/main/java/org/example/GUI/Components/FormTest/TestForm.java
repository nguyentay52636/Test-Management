package org.example.GUI.Components.FormTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.example.BUS.TopicBUS;
import org.example.DTO.TopicsDTO;

public class TestForm extends JPanel {
    private JPanel contentPanel; // Panel chứa giao diện chính

    public TestForm(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(45, 50, 70));

        // Tiêu đề
        JLabel lblTitle = new JLabel("Chọn Chủ Đề Để Thi!", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 32));
        lblTitle.setForeground(Color.WHITE);
        add(lblTitle, BorderLayout.NORTH);

        // Panel chứa danh sách chủ đề
        JPanel topicsListPanel = new JPanel();
        topicsListPanel.setLayout(new GridLayout(0, 3, 20, 20)); // Hiển thị 3 cột
        topicsListPanel.setBackground(new Color(45, 50, 70));

        // Lấy danh sách chủ đề từ database
        TopicBUS topicsBUS = new TopicBUS();
        List<TopicsDTO> topicsList = topicsBUS.getAllTopics();

        if (topicsList.isEmpty()) {
            JLabel noTopicsLabel = new JLabel("Không có chủ đề nào!", SwingConstants.CENTER);
            noTopicsLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
            noTopicsLabel.setForeground(Color.RED);
            add(noTopicsLabel, BorderLayout.CENTER);
        } else {
            for (TopicsDTO topic : topicsList) {
                JButton btnTopic = new JButton(topic.getTpTitle());
                btnTopic.setFont(new Font("Segoe UI", Font.BOLD, 20));
                btnTopic.setBackground(new Color(70, 130, 180));
                btnTopic.setForeground(Color.WHITE);
                btnTopic.setFocusPainted(false);

                // Xử lý khi chọn chủ đề
                btnTopic.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        openExamUI(topic.getTopicID());
                    }
                });

                topicsListPanel.add(btnTopic);
            }
            add(topicsListPanel, BorderLayout.CENTER);
        }
    }

    private void openExamUI(int topicID) {
        if (contentPanel != null) {
            TestUI examUI = new TestUI(contentPanel, topicID); // Truyền topicID để lấy bài thi
            contentPanel.removeAll();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(examUI, BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }
}
