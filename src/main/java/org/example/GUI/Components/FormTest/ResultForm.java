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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.example.DTO.UsersDTO;

public class ResultForm extends JDialog {
    private JPanel contentPanel;
    private boolean success;
    private double score;
    private int correctCount;
    private int totalQuestions;
    private UsersDTO currentUser;
    private String testCode;
    private int initialTime;
    private int remainingTime;
    private String timeTaken;

    public ResultForm(JPanel contentPanel, boolean success, double score, int correctCount, int totalQuestions,
            UsersDTO currentUser, String testCode, int initialTime, int remainingTime, String timeTaken) {
        super((java.awt.Frame) null, "Kết Quả Bài Kiểm Tra", true);
        this.contentPanel = contentPanel;
        this.success = success;
        this.score = score;
        this.correctCount = correctCount;
        this.totalQuestions = totalQuestions;
        this.currentUser = currentUser;
        this.testCode = testCode;
        this.initialTime = initialTime;
        this.remainingTime = remainingTime;
        this.timeTaken = timeTaken;

        initComponents();
        setSize(500, 450);
        setLocationRelativeTo(contentPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 20));
        getContentPane().setBackground(new Color(245, 247, 250));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(46, 204, 113), 0, getHeight(), new Color(39, 174, 96)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        add(headerPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel(success ? "Hoàn Thành Bài Kiểm Tra" : "Lỗi Kết Quả");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        add(contentPanel, BorderLayout.CENTER);

        Font textFont = new Font("Segoe UI", Font.BOLD, 18);

        if (success) {
            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel nameLabel = new JLabel("Họ và tên:");
            nameLabel.setFont(textFont);
            contentPanel.add(nameLabel, gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            JLabel nameValue = new JLabel(
                    currentUser.getUserFullName() != null ? currentUser.getUserFullName() : "N/A");
            nameValue.setFont(textFont);
            contentPanel.add(nameValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            JLabel idLabel = new JLabel("Mã thí sinh:");
            idLabel.setFont(textFont);
            contentPanel.add(idLabel, gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            JLabel idValue = new JLabel(String.valueOf(currentUser.getUserID()));
            idValue.setFont(textFont);
            contentPanel.add(idValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            JLabel codeLabel = new JLabel("Mã bài kiểm tra:");
            codeLabel.setFont(textFont);
            contentPanel.add(codeLabel, gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            JLabel codeValue = new JLabel(testCode);
            codeValue.setFont(textFont);
            contentPanel.add(codeValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            JLabel correctLabel = new JLabel("Số câu đúng:");
            correctLabel.setFont(textFont);
            contentPanel.add(correctLabel, gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            JLabel correctValue = new JLabel(correctCount + "/" + totalQuestions);
            correctValue.setFont(textFont);
            contentPanel.add(correctValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            JLabel scoreLabel = new JLabel("Kết quả:");
            scoreLabel.setFont(textFont);
            contentPanel.add(scoreLabel, gbc);
            gbc.gridx = 1;
            gbc.gridy = 4;
            JLabel scoreValue = new JLabel(String.format("%.2f/10", score));
            scoreValue.setFont(textFont);
            scoreValue.setForeground(new Color(39, 174, 96));
            contentPanel.add(scoreValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            JLabel timeLabel = new JLabel("Thời gian:");
            timeLabel.setFont(textFont);
            contentPanel.add(timeLabel, gbc);
            gbc.gridx = 1;
            gbc.gridy = 5;
            JLabel timeValue = new JLabel(timeTaken);
            timeValue.setFont(textFont);
            contentPanel.add(timeValue, gbc);
        } else {
            gbc.gridx = 0;
            gbc.gridy = 0;
            JLabel errorLabel = new JLabel("Có lỗi khi lưu kết quả bài kiểm tra!");
            errorLabel.setFont(textFont);
            errorLabel.setForeground(new Color(231, 76, 60));
            contentPanel.add(errorLabel, gbc);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        JButton backButton = createStyledButton("Trở lại", new Color(231, 76, 60), Color.WHITE);
        backButton.addActionListener(e -> {
            if (contentPanel != null) {
                contentPanel.removeAll();
                contentPanel.setLayout(new BorderLayout());
                TestForm examForm = new TestForm(contentPanel);
                contentPanel.add(examForm, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
            dispose();
        });
        buttonPanel.add(backButton);

        JButton closeButton = createStyledButton("Đóng", new Color(108, 117, 125), Color.WHITE);
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

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
                button.setBackground(bgColor.brighter());
            }
        });

        return button;
    }
}