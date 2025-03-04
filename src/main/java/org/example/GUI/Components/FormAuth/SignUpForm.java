package org.example.GUI.Components.FormAuth;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.example.BUS.UserBUS;
import org.example.DTO.UsersDTO;

public class SignUpForm extends javax.swing.JFrame {

        private JPanel panel1;
        UserBUS userBUS = new UserBUS();

        public SignUpForm() {
                initComponents();
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();

                jLabel6 = new javax.swing.JLabel();

                jLabel7 = new javax.swing.JLabel();

                jLabel8 = new javax.swing.JLabel();
                signUp = new javax.swing.JButton();
                backLogin = new javax.swing.JButton();
                txtUsername = new javax.swing.JTextField();
                labelFullName = new javax.swing.JLabel();
                txtFullName = new javax.swing.JTextField();
                txtPassword = new javax.swing.JPasswordField();
                txtEmail = new javax.swing.JTextField();
                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Sign Up");
                setPreferredSize(new java.awt.Dimension(800, 500));
                setLocationRelativeTo(null);
                setVisible(true);

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
                jPanel1.setLayout(null);

                jPanel2.setBackground(new java.awt.Color(0, 102, 102));

                jLabel1.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/org/example/GUI/menu/logo/logojavawing.png")));

                jLabel2.setFont(new java.awt.Font("Showcard Gothic", 0, 20)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                jLabel2.setText("QUẢN LÝ THI TRẮC NGHIỆM");

                jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(204, 204, 204));
                jLabel3.setText("copyright © company name All rights reserved");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(137, 137, 137)
                                                                                                .addComponent(jLabel1))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(72, 72, 72)
                                                                                                .addComponent(jLabel3))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addComponent(jLabel2)))
                                                                .addContainerGap(49, Short.MAX_VALUE)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(129, 129, 129)
                                                                .addComponent(jLabel1)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                108, Short.MAX_VALUE)
                                                                .addComponent(jLabel3)
                                                                .addGap(64, 64, 64)));

                jPanel1.add(jPanel2);
                jPanel2.setBounds(0, 0, 400, 500);

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                jLabel4.setBackground(new java.awt.Color(0, 102, 102));
                jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
                jLabel4.setText("SIGN UP");
                labelFullName.setBackground(new java.awt.Color(102, 102, 102));
                labelFullName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                labelFullName.setText("Full name");
                jLabel5.setBackground(new java.awt.Color(102, 102, 102));
                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel5.setText("User name");

                txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtUsername.setForeground(new java.awt.Color(102, 102, 102));
                txtFullName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtFullName.setForeground(new java.awt.Color(102, 102, 102));

                jLabel6.setBackground(new java.awt.Color(102, 102, 102));
                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel6.setText("Email");

                txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtEmail.setForeground(new java.awt.Color(102, 102, 102));

                jLabel7.setBackground(new java.awt.Color(102, 102, 102));
                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel7.setText("Password");

                txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtPassword.setForeground(new java.awt.Color(102, 102, 102));

                jLabel8.setText("I've an account");

                signUp.setBackground(new java.awt.Color(0, 102, 102));
                signUp.setForeground(new java.awt.Color(255, 255, 255));
                signUp.setOpaque(true);
                signUp.setContentAreaFilled(true);
                signUp.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 1));
                signUp.setEnabled(true);

                signUp.setText("Sign Up");
                backLogin.setForeground(new java.awt.Color(255, 51, 51));
                backLogin.setText("Login");
                signUp.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // Lấy dữ liệu từ form
                                String username = txtUsername.getText().trim();
                                String fullName = txtFullName.getText().trim();
                                String email = txtEmail.getText().trim();
                                String password = txtPassword.getText().trim();

                                // Kiểm tra trùng lặp tên đăng nhập hoặc email
                                for (UsersDTO user : userBUS.getListAccount()) {
                                        if (user.getUserName().equalsIgnoreCase(username)
                                                        || user.getUserEmail().equalsIgnoreCase(email)) {
                                                JOptionPane.showMessageDialog(null,
                                                                "Tên đăng nhập hoặc Email đã tồn tại! Vui lòng chọn tên khác.",
                                                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                                                return;
                                        }
                                }

                                // Kiểm tra các trường không được để trống
                                if (username.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi",
                                                        JOptionPane.ERROR_MESSAGE);
                                        return;
                                }

                                // Kiểm tra định dạng email
                                if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                                        JOptionPane.showMessageDialog(null, "Email không hợp lệ! Vui lòng nhập lại.",
                                                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                                        return;
                                }

                                // Kiểm tra độ dài mật khẩu (phải từ 6 ký tự trở lên)
                                if (password.length() < 6) {
                                        JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi",
                                                        JOptionPane.ERROR_MESSAGE);
                                        return;
                                }

                                // Tạo user mới
                                UsersDTO newUser = new UsersDTO();
                                newUser.setUserName(username);
                                newUser.setUserFullName(fullName);
                                newUser.setUserEmail(email);
                                newUser.setUserPassword(password);
                                newUser.setIsAdmin(false);

                                // Thêm vào database
                                boolean success = userBUS.insertUser(newUser);
                                if (success) {
                                        JOptionPane.showMessageDialog(null, "Đăng ký thành công! 🎉", "Thành công",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                        LoginForm loginForm = new LoginForm();
                                        loginForm.setVisible(true);
                                        loginForm.pack();
                                        loginForm.setLocationRelativeTo(null);
                                        dispose();
                                } else {
                                        JOptionPane.showMessageDialog(null, "Đăng ký thất bại! Vui lòng thử lại.",
                                                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                                }
                        }
                });

                backLogin.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                LoginForm LoginFrame = new LoginForm();
                                LoginFrame.setVisible(true);
                                LoginFrame.pack();
                                LoginFrame.setLocationRelativeTo(null);
                                dispose();
                                /*
                                 * setVisible(false);
                                 * SignUpForm signUp = new SignUpForm();
                                 * signUp.setVisible(false);
                                 */
                        }
                });

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(100, 100, 100)
                                                                                                .addComponent(jLabel4))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(44, 44, 44)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addComponent(labelFullName)
                                                                                                                                .addComponent(txtFullName)
                                                                                                                                .addComponent(jLabel5)
                                                                                                                                .addComponent(txtUsername)
                                                                                                                                .addComponent(jLabel6)
                                                                                                                                .addComponent(txtEmail,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                332,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(jLabel7)
                                                                                                                                .addComponent(txtPassword))
                                                                                                                .addComponent(signUp,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                91,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel8)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(backLogin,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                84,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                .addContainerGap(24, Short.MAX_VALUE)));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(28, 28, 28)
                                                                .addComponent(jLabel4)
                                                                .addGap(8, 8, 8)
                                                                .addComponent(jLabel5)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtUsername,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                40,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(8, 8, 8)
                                                                .addComponent(labelFullName)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtFullName,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                40,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(8, 8, 8)
                                                                .addComponent(jLabel6)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtEmail,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                40,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(8, 8, 8)
                                                                .addComponent(jLabel7)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtPassword,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                40,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(signUp,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                37,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(20, 20, 20)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel8)
                                                                                .addComponent(backLogin,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                31,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(45, Short.MAX_VALUE)));

                jPanel1.add(jPanel3);
                jPanel3.setBounds(400, 0, 400, 500);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 113, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 126, Short.MAX_VALUE)));

                pack();

        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton signUp;
        private javax.swing.JButton backLogin;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPasswordField txtPassword;
        private javax.swing.JTextField txtUsername;
        private javax.swing.JTextField txtEmail;
        private javax.swing.JLabel labelFullName;
        private javax.swing.JTextField txtFullName;

        // End of variables declaration//GEN-END:variables
        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> {
                        SignUpForm loginForm = new SignUpForm();
                        loginForm.setVisible(true);
                });
        }
}