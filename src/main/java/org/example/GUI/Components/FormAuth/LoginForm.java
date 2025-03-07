package org.example.GUI.Components.FormAuth;

import java.awt.Cursor;
import java.util.function.Consumer;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.example.BUS.UserBUS;
import org.example.DTO.SessionManager;
import org.example.DTO.UsersDTO;
import org.example.GUI.Application.Application;

public class LoginForm extends javax.swing.JFrame {
        UserBUS userBUS = new UserBUS();
        private Runnable loginSuccessListener;
        private JPanel Left;
        private JPanel Right;
        private javax.swing.JButton btnForgotPassword;
        private javax.swing.JButton btnLogin;
        private javax.swing.JButton jButton2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPasswordField passwordField;
        private javax.swing.JTextField emailField;
        SessionManager sessionManager = new SessionManager();
        public LoginForm() {
                initComponents();
                if (loginSuccessListener != null) {
                        loginSuccessListener.run();
                }
        }

        private void initComponents() {
                jPanel1 = new javax.swing.JPanel();
                Right = new javax.swing.JPanel();
                jLabel5 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                Left = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                emailField = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                passwordField = new javax.swing.JPasswordField();
                btnLogin = new javax.swing.JButton();
                jLabel4 = new javax.swing.JLabel();
                jButton2 = new javax.swing.JButton();
                btnForgotPassword = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("LOGIN");
                setPreferredSize(new java.awt.Dimension(800, 500));
                /* setLocationRelativeTo(null); */
                setVisible(true);

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
                jPanel1.setLayout(null);
      // sự kiện khi nhấn enter
      passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        });
    
                Right.setBackground(new java.awt.Color(0, 102, 102));
                Right.setPreferredSize(new java.awt.Dimension(400, 500));

                jLabel5.setIcon(new javax.swing.ImageIcon(
                                getClass().getResource("/org/example/GUI/menu/logo/logojavawing.png")));

                jLabel6.setFont(new java.awt.Font("Showcard Gothic", 1, 20)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                jLabel6.setText("QUẢN LÝ THI TRẮC NGHIỆM");

                jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(204, 204, 204));
                jLabel7.setText("copyright © company name All rights reserved");

                javax.swing.GroupLayout RightLayout = new javax.swing.GroupLayout(Right);
                Right.setLayout(RightLayout);
                RightLayout.setHorizontalGroup(
                                RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                RightLayout.createSequentialGroup()
                                                                                .addGap(0, 81, Short.MAX_VALUE)
                                                                                .addComponent(jLabel7)
                                                                                .addGap(40, 40, 40))
                                                .addGroup(RightLayout.createSequentialGroup()
                                                                .addGroup(RightLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(RightLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(50, 50, 50)
                                                                                                .addComponent(jLabel6))
                                                                                .addGroup(RightLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(145, 145, 145)
                                                                                                .addComponent(jLabel5)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                RightLayout.setVerticalGroup(
                                RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(RightLayout.createSequentialGroup()
                                                                .addGap(136, 136, 136)
                                                                .addComponent(jLabel5)
                                                                .addGap(26, 26, 26)
                                                                .addComponent(jLabel6)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                91, Short.MAX_VALUE)
                                                                .addComponent(jLabel7)
                                                                .addGap(78, 78, 78)));

                jPanel1.add(Right);
                Right.setBounds(0, 0, 400, 500);

                Left.setBackground(new java.awt.Color(255, 255, 255));
                Left.setMinimumSize(new java.awt.Dimension(400, 500));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(0, 102, 102));
                jLabel1.setText("LOGIN");

                jLabel2.setBackground(new java.awt.Color(102, 102, 102));
                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel2.setText("Email");

                emailField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                emailField.setForeground(new java.awt.Color(102, 102, 102));

                jLabel3.setBackground(new java.awt.Color(102, 102, 102));
                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel3.setText("Password");

                btnLogin.setBackground(new java.awt.Color(0, 102, 102));
                btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                btnLogin.setForeground(new java.awt.Color(255, 255, 255));
                btnLogin.setOpaque(true);
                btnLogin.setContentAreaFilled(true);
                btnLogin.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 1));
                btnLogin.setEnabled(true);
                btnLogin.setText("Login");
                btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                btnLogin.setBackground(new java.awt.Color(0, 153, 153)); // Màu sáng hơn khi hover
                        }

                        @Override
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                btnLogin.setBackground(new java.awt.Color(0, 102, 102)); // Trả về màu gốc
                        }
                });
                btnLogin.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            String email = emailField.getText().trim();
                            String password = new String(passwordField.getPassword()).trim();
                    
                            // Kiểm tra dữ liệu đầu vào
                            if (email.isEmpty() || password.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Email và mật khẩu không được để trống!",
                                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                    
                            UsersDTO foundUser = null;
                            for (UsersDTO user : userBUS.getListAccount()) {
                                if (user.getUserEmail().equals(email)) {
                                    foundUser = user;
                                    userBUS.setCurrentUserName(user.getUserName());
                                    break;
                                }
                            }
                    
                            if (foundUser == null) {
                                JOptionPane.showMessageDialog(null, "Email không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                    
                            System.out.println("Mật khẩu trong DB: " + foundUser.getUserPassword());
                            
                            // Kiểm tra mật khẩu
                            if (foundUser.getUserPassword().equals(password)) {
                                // Lưu thông tin người dùng vào session
                                sessionManager.setCurrentUser(foundUser);
                                JOptionPane.showMessageDialog(null, "Đăng nhập thành công! 🎉", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    
                                // Lưu trạng thái đăng nhập vào file (ví dụ: Preferences hoặc JSON)
                                saveLoginStatus(foundUser);
                    
                                // Gọi listener khi đăng nhập thành công (nếu có)
                                if (loginSuccessListener != null) {
                                    SwingUtilities.invokeLater(loginSuccessListener);
                                }
                    
                            } else {
                                JOptionPane.showMessageDialog(null, "Mật khẩu không đúng! ❌", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                btnForgotPassword = new javax.swing.JButton();
                btnForgotPassword.setFont(new java.awt.Font("Segoe UI", 0, 14));
                btnForgotPassword.setForeground(new java.awt.Color(0, 102, 255));
                btnForgotPassword.setText("Forgot Password?");
                btnForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnForgotPassword.setBorderPainted(false);
                btnForgotPassword.setContentAreaFilled(false);
                btnForgotPassword.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // Mở form reset password (hoặc hiển thị thông báo)
                                javax.swing.JOptionPane.showMessageDialog(null,
                                                "Redirecting to Forgot Password screen...");
                        }
                });

                jLabel4.setText("I don't have an account");

                jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jButton2.setForeground(new java.awt.Color(255, 51, 51));
                jButton2.setText("Sign Up");
                jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                SwingUtilities.invokeLater(() -> {
                                        SignUpForm signUp = new SignUpForm();
                                        signUp.setVisible(true);
                                        signUp.setLocationRelativeTo(null);
                                        dispose();
                                        signUp.pack();
                                        /*
                                         * LoginForm login = new LoginForm();
                                         * login.setVisible(false);
                                         */
                                });
                        }
                });

                javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
                Left.setLayout(LeftLayout);
                LeftLayout.setHorizontalGroup(
                                LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(LeftLayout.createSequentialGroup()
                                                                .addGroup(LeftLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(LeftLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(138, 138, 138)
                                                                                                .addComponent(jLabel1))
                                                                                .addGroup(LeftLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(30, 30, 30)
                                                                                                .addGroup(LeftLayout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(LeftLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addComponent(jLabel2)
                                                                                                                                .addComponent(emailField)
                                                                                                                                .addComponent(jLabel3)
                                                                                                                                .addComponent(passwordField,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                343,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(btnLogin,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                93,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(LeftLayout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel4)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(jButton2)))))
                                                                .addContainerGap(27, Short.MAX_VALUE)));
                LeftLayout.setVerticalGroup(
                                LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(LeftLayout.createSequentialGroup()
                                                                .addGap(51, 51, 51)
                                                                .addComponent(jLabel1)
                                                                .addGap(40, 40, 40)
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(emailField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                40,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(passwordField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                40,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(26, 26, 26)
                                                                .addComponent(btnLogin,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                36,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(33, 33, 33)
                                                                .addGroup(LeftLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel4)
                                                                                .addComponent(jButton2))
                                                                .addContainerGap(77, Short.MAX_VALUE)));

                jPanel1.add(Left);
                Left.setBounds(400, 0, 400, 500);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 129, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 149, Short.MAX_VALUE)));

                getAccessibleContext().setAccessibleName("LOGIN");

                pack();
        }

        public void setLoginSuccessListener(Runnable listener) {
                this.loginSuccessListener = listener;
        }
        
private void saveLoginStatus(UsersDTO user) {
    // Giả sử bạn lưu trạng thái đăng nhập vào Preferences (có thể thay bằng JSON hoặc cơ sở dữ liệu)
    Preferences prefs = Preferences.userNodeForPackage(Application.class);
    prefs.put("currentUserEmail", user.getUserEmail());
    prefs.put("currentUserName", user.getUserName());
    prefs.put("isLoggedIn", "true"); // Đánh dấu là đã đăng nhập
    // Cập nhật thêm thông tin nếu cần
    System.out.println("Thông tin người dùng đã được lưu.");
}
        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> {
                        SignUpForm loginForm = new SignUpForm();
                        loginForm.setVisible(true);
                });
        }

}
