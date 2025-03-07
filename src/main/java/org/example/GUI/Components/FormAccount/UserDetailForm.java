package org.example.GUI.Components.FormAccount;

import javax.swing.*;

import org.example.BUS.UserBUS;
import org.example.DTO.SessionManager;
import org.example.DTO.UsersDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserDetailForm extends JPanel {
    private JTextField txtUserName, txtUserEmail, txtFullName;
    private JPasswordField txtPassword;
    private JButton btnSave, btnCancel;
    public UsersDTO currentUser; 
    UserBUS userBUS = new UserBUS();


    public UserDetailForm() {
        currentUser = SessionManager.getCurrentUser();
userBUS = new UserBUS();
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
        txtUserEmail.setEnabled(false);
        txtFullName.setText(currentUser.getUserFullName());
        txtUserEmail.setText(currentUser.getUserEmail());
        txtPassword.setText(currentUser.getUserPassword());
        txtUserName.setText(currentUser.getUserName());

        // Tạo Nút Lưu và Hủy
        ImageIcon iconXoa = new ImageIcon(
            getClass().getResource("/org/example/GUI/resources/images/icons8_delete_forever_30px_1.png"));
    ImageIcon IconSua = new ImageIcon(
            getClass().getResource("/org/example/GUI/resources/images/icons8_wrench_30px.png"));
        btnSave = new JButton("Lưu thay đổi");
        btnSave.setIcon(IconSua);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveAction();
             
            }
        });
   
        btnCancel = new JButton("Làm mới");
        btnCancel.setIcon(iconXoa);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                resetFields();
            }
        });

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

        add(panel, BorderLayout.CENTER);
    }
    private void resetFields() {
        txtPassword.setText("");  
        txtFullName.setText(""); 
    }
    private void handleSaveAction() {
        // Retrieve updated values from fields
        String newPassword = new String(txtPassword.getPassword()).trim();
        String newFullName = txtFullName.getText().trim();
        String userName = txtUserName.getText().trim();
        // Validate inputs
        if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtPassword.requestFocus();
            return;
        }
        if (newFullName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ và tên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtFullName.requestFocus();
            return;
        }
        if (newPassword.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtPassword.requestFocus();
            return;
        }
        if (userName.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtPassword.requestFocus();
            return;
        }

        // Update currentUser with new values
        currentUser.setUserPassword(newPassword);
        currentUser.setUserFullName(newFullName);
        currentUser.setUserName(userName);

        // Save changes using UserBUS
        if (userBUS.updateUser(currentUser)) {
            JOptionPane.showMessageDialog(this, "🎉 Lưu thông tin thành công cho người dùng " + currentUser.getUserName() + "!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Lưu thông tin thất bại! Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
  
    public JPanel getPanel() {
        return this;
    }
}