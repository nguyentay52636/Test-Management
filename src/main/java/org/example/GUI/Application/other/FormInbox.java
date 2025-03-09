package org.example.GUI.Application.other;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.example.GUI.Components.FormMonHoc.JPanelAnh;
import org.example.GUI.Components.FormMonHoc.JPanelGeneral;
import org.example.GUI.Components.FormMonHoc.JPanelSu;

public class FormInbox extends JPanel {
        private JPanel contentPanel;

        public FormInbox(JPanel contentPanel) {
                this.contentPanel = contentPanel;
                initComponents();
        }

        @SuppressWarnings("unchecked")
        private void initComponents() {
                // Panel setup
                setBackground(new Color(245, 247, 250)); // Light gray-blue, school-friendly background
                setBorder(new EmptyBorder(20, 20, 20, 20));
                setLayout(null);

                // Header Section
                JPanel headerPanel = new JPanel();
                headerPanel.setBackground(new Color(0, 102, 204)); // Deep blue for school branding
                headerPanel.setBounds(0, 0, 1000, 100); // Adjusted height for prominence
                headerPanel.setLayout(null);
                add(headerPanel);

                JLabel lblHeader = new JLabel("Hệ Thống Quản Lý Đề Thi");
                lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 20));
                lblHeader.setForeground(Color.WHITE);
                lblHeader.setBounds(30, 30, 400, 40);
                headerPanel.add(lblHeader);

                // Main Title
                JLabel lblTitle = new JLabel("Chọn Môn Học Để Quản Lý");
                lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
                lblTitle.setForeground(new Color(51, 51, 51)); // Dark gray for readability
                lblTitle.setBounds(300, 130, 400, 40);
                add(lblTitle);

                // Subject Buttons in Grid Layout
                JButton btnAnh = createStyledButton("Tiếng Anh", "/org/example/GUI/resources/images/english_icon.png",
                                "Quản lý câu hỏi môn Tiếng Anh");
                btnAnh.setBounds(150, 200, 220, 120); // Larger for visual impact
                btnAnh.addActionListener(e -> openJPanelAnh());
                add(btnAnh);

                JButton btnSu = createStyledButton("Lịch Sử", "/org/example/GUI/resources/images/history_icon.png",
                                "Quản lý câu hỏi môn Lịch Sử");
                btnSu.setBounds(390, 200, 220, 120);
                btnSu.addActionListener(e -> openJPanelSu());
                add(btnSu);

                JButton btnDiffer = createStyledButton("Tổng Hợp", "/org/example/GUI/resources/images/general_icon.png",
                                "Quản lý câu hỏi tổng hợp");
                btnDiffer.setBounds(630, 200, 220, 120);
                btnDiffer.addActionListener(e -> openJPanelGeneral());
                add(btnDiffer);

                // Additional Feature Button
                JButton btnDashboard = createStyledButton("Trang Chủ",
                                "/org/example/GUI/resources/images/dashboard_icon.png",
                                "Xem thống kê và thông tin tổng quan");
                btnDashboard.setBounds(390, 340, 220, 120);
                btnDashboard.addActionListener(e -> openDashboard());
                add(btnDashboard);

                // Footer
                JLabel lblFooter = new JLabel(" Hệ Thống Quản Lý Đề Thi");
                lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                lblFooter.setForeground(new Color(100, 100, 100));
                lblFooter.setBounds(350, 650, 300, 20);
                add(lblFooter);
        }

        private JButton createStyledButton(String text, String iconPath, String tooltip) {
                JButton button = new JButton();
                button.setText(text);
                button.setFont(new Font("Segoe UI", Font.BOLD, 18));
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(0, 102, 204)); // Blue text matching header
                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Subtle border
                                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Inner padding
                button.setToolTipText(tooltip);

                // Add icon if provided
                if (iconPath != null) {
                        java.net.URL imgURL = getClass().getResource(iconPath);
                        if (imgURL != null) {
                                button.setIcon(new ImageIcon(imgURL));
                                button.setHorizontalTextPosition(JButton.CENTER); // Text below icon
                                button.setVerticalTextPosition(JButton.BOTTOM);
                        } else {
                                System.out.println("Không tìm thấy icon: " + iconPath);
                        }
                }

                // Hover and press effects
                button.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                                button.setBackground(new Color(230, 240, 255)); // Light blue hover
                                button.setBorder(BorderFactory.createCompoundBorder(
                                                BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
                                                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                                button.setBackground(Color.WHITE);
                                button.setBorder(BorderFactory.createCompoundBorder(
                                                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                                                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                                button.setBackground(new Color(200, 220, 255)); // Slightly darker when pressed
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                                button.setBackground(new Color(230, 240, 255));
                        }
                });

                return button;
        }

        private void openJPanelAnh() {
                if (contentPanel != null) {
                        JPanelAnh jPanelAnh = new JPanelAnh(contentPanel);
                        contentPanel.removeAll();
                        contentPanel.add(jPanelAnh);
                        contentPanel.revalidate();
                        contentPanel.repaint();
                        System.out.println("Chuyển sang JPanelAnh!");
                }
        }

        private void openJPanelSu() {
                if (contentPanel != null) {
                        JPanelSu jPanelSu = new JPanelSu(contentPanel);
                        contentPanel.removeAll();
                        contentPanel.add(jPanelSu);
                        contentPanel.revalidate();
                        contentPanel.repaint();
                        System.out.println("Chuyển sang JPanelSu!");
                }
        }

        private void openJPanelGeneral() {
                if (contentPanel != null) {
                        JPanelGeneral jPanelGeneral = new JPanelGeneral(contentPanel);
                        contentPanel.removeAll();
                        contentPanel.add(jPanelGeneral);
                        contentPanel.revalidate();
                        contentPanel.repaint();
                        System.out.println("Chuyển sang JPanelGeneral!");
                }
        }

        private void openDashboard() {
                System.out.println("Chuyển sang Dashboard (chưa triển khai)!");
                // Placeholder for future dashboard implementation
        }
}