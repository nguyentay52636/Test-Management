package org.example.GUI.Application.other;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.example.GUI.Components.FormMonHoc.JPanelAnh;
import org.example.GUI.Components.FormMonHoc.JPanelGeneral;
import org.example.GUI.Components.FormMonHoc.JPanelSu;

public class FormInbox extends JPanel {
        private JPanel contentPanel; // Lưu panel chứa giao diện

        public FormInbox(JPanel contentPanel) {
                this.contentPanel = contentPanel;
                initComponents();
        }

        @SuppressWarnings("unchecked")
        private void initComponents() {
                JLabel lblNewLabel = new JLabel("Chọn Môn Để Quản Lý!");
                lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 32));

                JButton btnAnh = new JButton("Anh");
                btnAnh.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
                btnAnh.addActionListener(e -> openJPanelAnh()); // Xử lý sự kiện khi bấm nút

                JButton btnSu = new JButton("Sử");
                btnSu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
                btnSu.addActionListener(e -> openJPanelSu());

                JButton btnDiffer = new JButton("Khác");
                btnDiffer.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
                btnDiffer.addActionListener(e -> openJPanelGeneral());

                GroupLayout layout = new GroupLayout(this);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(109)
                                                                .addComponent(btnAnh, GroupLayout.PREFERRED_SIZE, 157,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(154)
                                                                .addComponent(btnSu, GroupLayout.PREFERRED_SIZE, 176,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(145)
                                                                .addComponent(btnDiffer, GroupLayout.PREFERRED_SIZE,
                                                                                166, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(128))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(346)
                                                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
                                                                                599, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(90)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(91)
                                                                .addComponent(lblNewLabel)
                                                                .addGap(137)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnAnh,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                56,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnSu,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                60,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnDiffer,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                62,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(252, Short.MAX_VALUE)));
                this.setLayout(layout);
        }

        private void openJPanelAnh() {
                if (contentPanel != null) {
                        JPanelAnh jPanelAnh = new JPanelAnh(contentPanel);
                        contentPanel.removeAll(); // Xóa JPanel hiện tại
                        contentPanel.add(jPanelAnh); // Thêm JPanelAnh mới
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
                        contentPanel.removeAll(); // Xóa JPanel hiện tại
                        contentPanel.add(jPanelGeneral); // Thêm JPanelAnh mới
                        contentPanel.revalidate();
                        contentPanel.repaint();
                        System.out.println("Chuyển sang JPanelGeneral!");
                }
        }

}
