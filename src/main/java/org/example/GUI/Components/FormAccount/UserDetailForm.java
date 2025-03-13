package org.example.GUI.Components.FormAccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.example.BUS.UserBUS;
import org.example.DTO.SessionManager;
import org.example.DTO.UsersDTO;

public class UserDetailForm extends JPanel {
    private JTextField txtUserName, txtUserEmail, txtFullName;
    private JPasswordField txtPassword;
    private JButton btnSave, btnCancel;
    private UsersDTO currentUser;
    private UserBUS userBUS;

    public UserDetailForm() {
        currentUser = SessionManager.getCurrentUser();
        userBUS = new UserBUS();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 245)); // Light gray background
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue header
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        JLabel lblTitle = new JLabel("Thông Tin Tài Khoản");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Consistent spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Labels and Fields
        JLabel lblUserName = createLabel("Tên đăng nhập:");
        JLabel lblEmail = createLabel("Email:");
        JLabel lblPassword = createLabel("Mật khẩu:");
        JLabel lblFullName = createLabel("Họ và Tên:");

        txtUserName = createTextField(currentUser.getUserName());
        txtUserEmail = createTextField(currentUser.getUserEmail());
        txtUserEmail.setEnabled(false);
        txtPassword = createPasswordField(currentUser.getUserPassword());
        txtFullName = createTextField(currentUser.getUserFullName());

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblUserName, gbc);
        gbc.gridx = 1;
        formPanel.add(txtUserName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        formPanel.add(txtUserEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblPassword, gbc);
        gbc.gridx = 1;
        formPanel.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblFullName, gbc);
        gbc.gridx = 1;
        formPanel.add(txtFullName, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        btnSave = createButton("Lưu thay đổi", new Color(34, 139, 34), "icons8_wrench_30px.png"); // Forest Green
        btnCancel = createButton("Làm mới", new Color(178, 34, 34), "icons8_delete_forever_30px_1.png"); // Firebrick
                                                                                                         // Red

        btnSave.addActionListener(e -> handleSaveAction());
        btnCancel.addActionListener(e -> resetFields());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(70, 70, 70));
        return label;
    }

    private JTextField createTextField(String text) {
        JTextField field = new JTextField(text, 20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        field.setBackground(Color.WHITE);
        field.setForeground(new Color(50, 50, 50));
        return field;
    }

    private JPasswordField createPasswordField(String text) {
        JPasswordField field = new JPasswordField(text, 20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        field.setBackground(Color.WHITE);
        field.setForeground(new Color(50, 50, 50));
        return field;
    }

    private JButton createButton(String text, Color bgColor, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setPreferredSize(new Dimension(140, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        URL iconUrl = getClass().getResource("/org/example/GUI/resources/images/" + iconPath);
        if (iconUrl != null) {
            button.setIcon(new ImageIcon(iconUrl));
        } else {
            System.out.println("Icon not found: " + iconPath);
        }

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
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void resetFields() {
        txtPassword.setText("");
        txtFullName.setText("");
        txtUserName.setText(currentUser.getUserName());
        txtUserEmail.setText(currentUser.getUserEmail());
    }

    private void handleSaveAction() {
        String newPassword = new String(txtPassword.getPassword()).trim();
        String newFullName = txtFullName.getText().trim();
        String userName = txtUserName.getText().trim();

        // Validation
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
        if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtUserName.requestFocus();
            return;
        }
        if (newPassword.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtPassword.requestFocus();
            return;
        }
        if (userName.length() < 6) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập phải có ít nhất 6 ký tự!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            txtUserName.requestFocus();
            return;
        }

        // Update user data
        currentUser.setUserPassword(newPassword);
        currentUser.setUserFullName(newFullName);
        currentUser.setUserName(userName);

        if (userBUS.updateUser(currentUser)) {
            JOptionPane.showMessageDialog(this,
                    "🎉 Lưu thông tin thành công cho người dùng " + currentUser.getUserName() + "!", "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Lưu thông tin thất bại! Vui lòng thử lại.", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getPanel() {
        return this;
    }
}