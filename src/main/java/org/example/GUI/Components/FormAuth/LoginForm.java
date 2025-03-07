package org.example.GUI.Components.FormAuth;

import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.example.BUS.UserBUS;
import org.example.DTO.SessionManager;
import org.example.DTO.UsersDTO;
import org.example.GUI.Application.Application;
import org.example.GUI.FormDialog.DiaLogForgetPass.DiaLogChangePass;

import net.miginfocom.swing.MigLayout;

public class LoginForm extends JPanel {
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
    private static final String LOGIN_INFO_FILE = "last_login.txt";

    public LoginForm() {
        initComponents();
        setLoginSuccessListener(() -> Application.login());
        loadLastLoginInfo(); // Load saved login info when form initializes
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
    
        setLayout(new MigLayout("al center center", "[grow]", "[grow]"));
    
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new MigLayout("ins 0", "[400px][grow]", "[500px]"));
    
        // Left Panel (Logo and Text)
        Left.setBackground(new java.awt.Color(0, 102, 102));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/example/GUI/menu/logo/logojavawing.png")));
        jLabel6.setFont(new java.awt.Font("Showcard Gothic", 1, 20));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("QUẢN LÝ THI TRẮC NGHIỆM");
        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14));
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("copyright © company name All rights reserved");
    
        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(LeftLayout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jLabel6))
                .addGroup(LeftLayout.createSequentialGroup()
                    .addGap(145, 145, 145)
                    .addComponent(jLabel5))
                .addGroup(LeftLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jLabel7))
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addGroup(LeftLayout.createSequentialGroup()
                    .addGap(136, 136, 136)
                    .addComponent(jLabel5)
                    .addGap(26, 26, 26)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addGap(78, 78, 78))
        );
    
        // Right Panel (Login Form)
        Right.setBackground(new java.awt.Color(255, 255, 255));
        Right.setLayout(new MigLayout("al center, wrap 1", "[grow]", "[grow]"));
    
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("LOGIN");
    
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel2.setText("Email");
        emailField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        });
    
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel3.setText("Password");
        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 14));
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLogin.doClick();
                }
            }
        });
    
        btnLogin.setBackground(new java.awt.Color(0, 102, 102));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new java.awt.Color(0, 153, 153));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new java.awt.Color(0, 102, 102));
            }
        });
        btnLogin.addActionListener(e -> performLogin());
    
        btnForgotPassword.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btnForgotPassword.setForeground(new java.awt.Color(0, 102, 255));
        btnForgotPassword.setText("Forgot Password?");
        btnForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnForgotPassword.setBorderPainted(false);
        btnForgotPassword.setContentAreaFilled(false);
        btnForgotPassword.addActionListener(e -> JOptionPane.showMessageDialog(null, "Redirecting to Forgot Password screen..."));
    
        jLabel4.setText("I don't have an account");
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Sign Up");
        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton2.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                SignUpForm signUp = new SignUpForm();
                Application.getInstance().setContentPane(signUp);
                Application.getInstance().revalidate();
                Application.getInstance().repaint();
            });
        });
    
        Right.add(jLabel1, "center, wrap");
        Right.add(jLabel2, "center, wrap");
        Right.add(emailField, "width 343px, center, wrap");
        Right.add(jLabel3, "center, wrap");
        Right.add(passwordField, "width 343px, center, wrap");
        Right.add(btnLogin, "width 93px, center, wrap");
        Right.add(btnForgotPassword, "center, wrap");
        Right.add(jLabel4, "split 2, center");
        Right.add(jButton2, "wrap");
    
        jPanel1.add(Left, "width pref, height 500px");
        jPanel1.add(Right, "width pref, height 500px");
    
        add(jPanel1, "center");
    }

    private void performLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email và mật khẩu không được để trống!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsersDTO foundUser = null;
        for (UsersDTO user : userBUS.getListAccount()) {
            if (user.getUserEmail().equals(email)) {
                foundUser = user;
                break;
            }
        }

        if (foundUser == null) {
            JOptionPane.showMessageDialog(null, "Email không tồn tại!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Mật khẩu trong DB: " + foundUser.getUserPassword());
        if (foundUser.getUserPassword().equals(password)) {
            sessionManager.setCurrentUser(foundUser);
            saveLoginInfo(email, password); // Save login info
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công! 🎉", "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
UsersDTO currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            String currentUsername = currentUser.getUserName(); // Assuming UsersDTO has a getUserName() method
            System.out.println("Current username: " + currentUsername);
                
        } else {
            System.err.println("Current user is null after login!");
        }
            if (loginSuccessListener != null) {
                SwingUtilities.invokeLater(loginSuccessListener);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mật khẩu không đúng! ❌", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveLoginInfo(String email, String password) {
        try (FileWriter writer = new FileWriter(LOGIN_INFO_FILE)) {
            writer.write(email + "\n");
            writer.write(password + "\n");
        } catch (IOException e) {
            System.err.println("Error saving login info: " + e.getMessage());
        }
    }

    private void loadLastLoginInfo() {
        File file = new File(LOGIN_INFO_FILE);
        if (file.exists()) {
            try {
                String[] lines = Files.readAllLines(Paths.get(LOGIN_INFO_FILE)).toArray(new String[0]);
                if (lines.length >= 2) {
                    emailField.setText(lines[0]);
                    passwordField.setText(lines[1]);
                }
            } catch (IOException e) {
                System.err.println("Error loading login info: " + e.getMessage());
            }
        }
    }

    public void setLoginSuccessListener(Runnable listener) {
        this.loginSuccessListener = listener;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("Login Form Test");
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            frame.add(new LoginForm());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}