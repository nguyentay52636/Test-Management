package org.example.GUI.Components.FormAuth;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.example.BUS.UserBUS;
import org.example.DTO.UsersDTO;
import org.example.GUI.Application.Application;

import net.miginfocom.swing.MigLayout;

public class SignUpForm extends JPanel {

    private JPanel jPanel1;
    UserBUS userBUS = new UserBUS();

    // Component declarations
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel labelFullName;
    private javax.swing.JTextField txtFullName;

    public SignUpForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
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
        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();

        setLayout(new MigLayout("al center center", "[grow]", "[grow]"));
        setPreferredSize(new java.awt.Dimension(800, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new MigLayout("ins 0", "[pref][grow]", "[500px]"));

        // Left Panel (Logo and Text)
        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/example/GUI/menu/logo/logojavawing.png")));
        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 0, 20));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("QUẢN LÝ THI TRẮC NGHIỆM");
        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14));
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("copyright © company name All rights reserved");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jLabel2))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(145, 145, 145)
                    .addComponent(jLabel1))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jLabel3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(jLabel1)
                    .addGap(30, 30, 30)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(64, 64, 64))
        );

        // Right Panel (Sign Up Form)
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new MigLayout("al center, wrap 1", "[grow]", "[grow]"));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("SIGN UP");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel5.setText("User name");
        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtUsername.setForeground(new java.awt.Color(102, 102, 102));

        labelFullName.setFont(new java.awt.Font("Segoe UI", 0, 14));
        labelFullName.setText("Full name");
        txtFullName.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtFullName.setForeground(new java.awt.Color(102, 102, 102));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel6.setText("Email");
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtEmail.setForeground(new java.awt.Color(102, 102, 102));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel7.setText("Password");
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtPassword.setForeground(new java.awt.Color(102, 102, 102));

        jLabel8.setText("I've an account");

        signUp.setBackground(new java.awt.Color(0, 102, 102));
        signUp.setFont(new java.awt.Font("Segoe UI", 0, 14));
        signUp.setForeground(new java.awt.Color(255, 255, 255));
        signUp.setText("Sign Up");
        signUp.setOpaque(true);
        signUp.setContentAreaFilled(true);
        signUp.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 1));
        signUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signUp.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String fullName = txtFullName.getText().trim();
            String email = txtEmail.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            for (UsersDTO user : userBUS.getListAccount()) {
                if (user.getUserName().equalsIgnoreCase(username) || user.getUserEmail().equalsIgnoreCase(email)) {
                    JOptionPane.showMessageDialog(null,
                            "Tên đăng nhập hoặc Email đã tồn tại! Vui lòng chọn tên khác.",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (username.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                JOptionPane.showMessageDialog(null, "Email không hợp lệ! Vui lòng nhập lại.",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password.length() < 6) {
                JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            UsersDTO newUser = new UsersDTO();
            newUser.setUserName(username);
            newUser.setUserFullName(fullName);
            newUser.setUserEmail(email);
            newUser.setUserPassword(password);
            newUser.setIsAdmin(false);

            boolean success = userBUS.insertUser(newUser);
            if (success) {
                JOptionPane.showMessageDialog(null, "Đăng ký thành công! 🎉", "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
                SwingUtilities.invokeLater(() -> {
                    LoginForm loginForm = new LoginForm();
                    Application.getInstance().setContentPane(loginForm);
                    Application.getInstance().revalidate();
                    Application.getInstance().repaint();
                });
            } else {
                JOptionPane.showMessageDialog(null, "Đăng ký thất bại! Vui lòng thử lại.",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        backLogin.setFont(new java.awt.Font("Segoe UI", 0, 14));
        backLogin.setForeground(new java.awt.Color(255, 51, 51));
        backLogin.setText("Login");
        backLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backLogin.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                LoginForm loginForm = new LoginForm();
                Application.getInstance().setContentPane(loginForm);
                Application.getInstance().revalidate();
                Application.getInstance().repaint();
            });
        });

        jPanel3.add(jLabel4, "center, wrap");
        jPanel3.add(jLabel5, "center, wrap");
        jPanel3.add(txtUsername, "width 332px, center, wrap");
        jPanel3.add(labelFullName, "center, wrap");
        jPanel3.add(txtFullName, "width 332px, center, wrap");
        jPanel3.add(jLabel6, "center, wrap");
        jPanel3.add(txtEmail, "width 332px, center, wrap");
        jPanel3.add(jLabel7, "center, wrap");
        jPanel3.add(txtPassword, "width 332px, center, wrap");
        jPanel3.add(signUp, "width 91px, center, wrap");
        jPanel3.add(jLabel8, "split 2, center");
        jPanel3.add(backLogin, "wrap");

        jPanel1.add(jPanel2, "width pref, height 500px");
        jPanel1.add(jPanel3, "width pref, height 500px");

        add(jPanel1, "center");
    }

    // Test the panel standalone
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("Sign Up Form Test");
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            frame.add(new SignUpForm());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}