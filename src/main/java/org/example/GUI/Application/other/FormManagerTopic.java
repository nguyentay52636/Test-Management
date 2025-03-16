package org.example.GUI.Application.other;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.example.GUI.Components.FormMonHoc.JpanelEnglish;
import org.example.GUI.Components.FormMonHoc.JpanelMath;
import org.example.GUI.Components.FormMonHoc.SumaryQuestion;

public class FormManagerTopic extends JPanel {
        private JPanel contentPanel;

        public FormManagerTopic(JPanel contentPanel) {
                this.contentPanel = contentPanel;
                initComponents();
        }

        private void initComponents() {
                setBackground(new Color(240, 242, 245)); // Màu nền sáng, giống web app
                setBorder(new EmptyBorder(20, 20, 20, 20));
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(15, 15, 15, 15);

                // Header Section với Gradient
                JPanel headerPanel = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                Graphics2D g2d = (Graphics2D) g;
                                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                                GradientPaint gp = new GradientPaint(0, 0, new Color(52, 108, 176), getWidth(), 0,
                                                new Color(72, 128, 196));
                                g2d.setPaint(gp);
                                g2d.fillRect(0, 0, getWidth(), getHeight());
                        }
                };
                headerPanel.setPreferredSize(new Dimension(0, 80));
                headerPanel.setLayout(new BorderLayout());
                JLabel lblHeader = new JLabel("Hệ Thống Quản Lý Đề Thi", SwingConstants.CENTER);
                lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
                lblHeader.setForeground(Color.WHITE);
                lblHeader.setBorder(new EmptyBorder(10, 0, 10, 0));
                headerPanel.add(lblHeader, BorderLayout.CENTER);
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 4; // Tăng gridwidth để header chiếm toàn bộ chiều ngang
                gbc.fill = GridBagConstraints.HORIZONTAL;
                add(headerPanel, gbc);

                // Main Title
                JLabel lblTitle = new JLabel("Chọn Môn Học Để Quản Lý");
                lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
                lblTitle.setForeground(new Color(33, 37, 41));
                gbc.gridy = 1;
                gbc.gridwidth = 4;
                gbc.insets = new Insets(20, 0, 30, 0); // Khoảng cách lớn hơn dưới tiêu đề
                gbc.anchor = GridBagConstraints.CENTER;
                add(lblTitle, gbc);

                // Subject Buttons Panel với Card Layout
                Dimension cardSize = new Dimension(220, 140); // Kích thước thẻ đồng đều
                gbc.gridwidth = 1;
                gbc.fill = GridBagConstraints.NONE;

                // Card Toán
                JPanel cardToan = createCard("Toán", "/org/example/GUI/resources/imageTopic/trangchu_icon.png",
                                "Quản lý câu hỏi môn Toán", e -> openJPanelToan());
                cardToan.setPreferredSize(cardSize);
                gbc.gridx = 0;
                gbc.gridy = 2;
                add(cardToan, gbc);

                // Card Tiếng Anh
                JPanel cardAnh = createCard("Tiếng Anh", "/org/example/GUI/resources/imageTopic/english_icon.png",
                                "Quản lý câu hỏi môn Tiếng Anh", e -> openJPanelAnh());
                cardAnh.setPreferredSize(cardSize);
                gbc.gridx = 1;
                gbc.gridy = 2;
                add(cardAnh, gbc);

                // Card Lịch Sử
                JPanel cardLichSu = createCard("Lịch Sử", "/org/example/GUI/resources/imageTopic/history.sql.png",
                                "Quản lý câu hỏi môn Lịch Sử", e -> openJPanelLichSu());
                cardLichSu.setPreferredSize(cardSize);
                gbc.gridx = 2;
                gbc.gridy = 2;
                add(cardLichSu, gbc);

                // Card Trang Chủ
                JPanel cardDashboard = createCard("Trang Chủ", "/org/example/GUI/resources/imageTopic/home_icon.png",
                                "Xem thống kê và thông tin tổng quan", e -> openDashboard());
                cardDashboard.setPreferredSize(cardSize);
                gbc.gridx = 3;
                gbc.gridy = 2;
                add(cardDashboard, gbc);

                // Footer
                JLabel lblFooter = new JLabel("© 2025 Hệ Thống Quản Lý Đề Thi", SwingConstants.CENTER);
                lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                lblFooter.setForeground(new Color(120, 120, 120));
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 4;
                gbc.weighty = 1.0; // Đẩy footer xuống dưới
                gbc.anchor = GridBagConstraints.SOUTH;
                gbc.insets = new Insets(30, 0, 10, 0);
                add(lblFooter, gbc);
        }

        private JPanel createCard(String text, String iconPath, String tooltip, java.awt.event.ActionListener action) {
                JPanel card = new JPanel();
                card.setBackground(Color.WHITE);
                card.setLayout(new BorderLayout());
                card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // Thêm shadow và bo góc
                card.setBorder(BorderFactory.createCompoundBorder(
                                new javax.swing.border.MatteBorder(0, 0, 2, 2, new Color(0, 0, 0, 30)), // Shadow nhẹ
                                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true))); // Viền bo góc

                // Icon
                JLabel iconLabel = new JLabel();
                if (iconPath != null) {
                        java.net.URL imgURL = getClass().getResource(iconPath);
                        if (imgURL != null) {
                                iconLabel.setIcon(new ImageIcon(imgURL));
                        } else {
                                System.out.println("Không tìm thấy icon: " + iconPath);
                        }
                }
                iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
                card.add(iconLabel, BorderLayout.CENTER);

                // Text
                JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
                textLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                textLabel.setForeground(new Color(52, 108, 176));
                card.add(textLabel, BorderLayout.SOUTH);

                // Hiệu ứng hover và click
                card.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                                card.setBackground(new Color(245, 248, 255));
                                card.setBorder(BorderFactory.createCompoundBorder(
                                                new javax.swing.border.MatteBorder(0, 0, 2, 2, new Color(0, 0, 0, 50)),
                                                BorderFactory.createLineBorder(new Color(52, 108, 176), 2, true)));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                                card.setBackground(Color.WHITE);
                                card.setBorder(BorderFactory.createCompoundBorder(
                                                new javax.swing.border.MatteBorder(0, 0, 2, 2, new Color(0, 0, 0, 30)),
                                                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true)));
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                                card.setBackground(new Color(230, 240, 255));
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                                card.setBackground(new Color(245, 248, 255));
                        }

                        @Override
                        public void mouseClicked(MouseEvent e) {
                                if (action != null) {
                                        action.actionPerformed(null);
                                }
                        }
                });

                card.setToolTipText(tooltip);
                return card;
        }

        private void openJPanelToan() {
                if (contentPanel != null) {
                        JpanelMath jPanelToan = new JpanelMath(contentPanel);
                        contentPanel.removeAll();
                        contentPanel.add(jPanelToan);
                        contentPanel.revalidate();
                        contentPanel.repaint();
                        System.out.println("Chuyển sang JpanelMath!");
                }
        }

        private void openJPanelAnh() {
                if (contentPanel != null) {
                        JpanelEnglish jPanelAnh = new JpanelEnglish(contentPanel);
                        contentPanel.removeAll();
                        contentPanel.add(jPanelAnh);
                        contentPanel.revalidate();
                        contentPanel.repaint();
                        System.out.println("Chuyển sang JpanelEnglish!");
                }
        }

        private void openJPanelLichSu() {
                if (contentPanel != null) {
                        SumaryQuestion jPanelLichSu = new SumaryQuestion(contentPanel);
                        contentPanel.removeAll();
                        contentPanel.add(jPanelLichSu);
                        contentPanel.revalidate();
                        contentPanel.repaint();
                        System.out.println("Chuyển sang JpanelHistory!");
                }
        }

        private void openDashboard() {
                System.out.println("Chuyển sang Dashboard (chưa triển khai)!");
                // Placeholder for future dashboard implementation
        }
}