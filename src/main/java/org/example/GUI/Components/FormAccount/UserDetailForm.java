package org.example.GUI.Components.FormAccount;

import javax.swing.*;
import java.awt.*;

public class UserDetailForm extends JPanel {
    private JTextField txtUserName, txtUserEmail, txtFullName;
    private JPasswordField txtPassword;
    private JButton btnSave, btnCancel;

    public UserDetailForm() {
        setLayout(new BorderLayout());

        // Tạo Panel chính
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 248, 255)); // Màu nền xanh nhẹ
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitle = new JLabel("Thông Tin Tài Khoản");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(new Color(30, 144, 255)); // Màu xanh đậm

        // Tạo các Label
        JLabel lblUserName = new JLabel("Tên đăng nhập:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPassword = new JLabel("Mật khẩu:");
        JLabel lblFullName = new JLabel("Họ và Tên:");

        // Tạo các TextField và PasswordField (mặc định rỗng)
        txtUserName = new JTextField();
        txtUserEmail = new JTextField();
        txtPassword = new JPasswordField();
        txtFullName = new JTextField();

        // Tạo Nút Lưu và Hủy
        btnSave = new JButton("Lưu thay đổi");
        btnSave.setBackground(new Color(50, 205, 50)); // Màu xanh lá
        btnSave.setForeground(Color.WHITE);
        btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(220, 20, 60)); // Màu đỏ
        btnCancel.setForeground(Color.WHITE);

        // Bố cục form bằng GroupLayout
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(lblTitle)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblUserName)
                        .addComponent(lblEmail)
                        .addComponent(lblPassword)
                        .addComponent(lblFullName))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtUserName)
                        .addComponent(txtUserEmail)
                        .addComponent(txtPassword)
                        .addComponent(txtFullName)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserName)
                    .addComponent(txtUserName))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtUserEmail))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFullName)
                    .addComponent(txtFullName))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
        );

        // Thêm panel vào JPanel chính
        add(panel, BorderLayout.CENTER);
    }

    // Phương thức lấy JPanel này để nhúng vào JFrame khác
    public JPanel getPanel() {
        return this;
    }
}
