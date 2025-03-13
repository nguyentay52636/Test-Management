package org.example.GUI.FormDialog.DiaLogForgetPass;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import org.example.BUS.UserBUS;
import org.example.DTO.SessionManager;
import org.example.DTO.UsersDTO;
import org.example.GUI.Application.Application;

public class DiaLogChangePass extends JPanel {
    private UsersDTO currentUser;
    private String currentUsername;
    private UserBUS userBUS = new UserBUS();
    private JPasswordField txMatKhauCu = new JPasswordField(20);
    private JPasswordField txMatKhauMoi = new JPasswordField(20);
    private JPasswordField txXacNhanMatKhau = new JPasswordField(20);
    private JButton btnDongY = new JButton("Đồng ý");
    private JButton btnHuy = new JButton("Hủy");

    public DiaLogChangePass(String username) {
        currentUser = SessionManager.getCurrentUser();
        currentUsername = currentUser != null ? currentUser.getUserName() : username; // Fallback to passed username if
                                                                                      // null
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 245)); // Light gray background for a clean look
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180)); // Steel blue for header
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitle = new JLabel("Đổi Mật Khẩu");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Input Panel
        JPanel plInput = new JPanel();
        plInput.setLayout(new GridBagLayout());
        plInput.setBackground(Color.WHITE);
        plInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Consistent spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Styling input fields
        configurePasswordField(txMatKhauCu, "Mật khẩu cũ:");
        configurePasswordField(txMatKhauMoi, "Mật khẩu mới:");
        configurePasswordField(txXacNhanMatKhau, "Xác nhận mật khẩu:");

        gbc.gridx = 0;
        gbc.gridy = 0;
        plInput.add(txMatKhauCu, gbc);

        gbc.gridy = 1;
        plInput.add(txMatKhauMoi, gbc);

        gbc.gridy = 2;
        plInput.add(txXacNhanMatKhau, gbc);

        add(plInput, BorderLayout.CENTER);

        // Button Panel
        JPanel plButton = new JPanel();
        plButton.setBackground(Color.WHITE);
        plButton.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        plButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        configureButton(btnDongY, new Color(34, 139, 34), Color.WHITE, "icons8_add_30px.png"); // Forest Green
        configureButton(btnHuy, new Color(178, 34, 34), Color.WHITE, "icons8_cancel_30px_1.png"); // Firebrick Red

        plButton.add(btnDongY);
        plButton.add(btnHuy);

        add(plButton, BorderLayout.SOUTH);

        // Action Listeners
        btnHuy.addActionListener(ae -> resetField());

        btnDongY.addActionListener(ae -> {
            if (checkPass()) {
                String newPassword = new String(txMatKhauMoi.getPassword());
                currentUser.setUserPassword(newPassword);

                if (userBUS.updateUser(currentUser)) {
                    JOptionPane.showMessageDialog(this,
                            "🎉 Đổi mật khẩu thành công cho người dùng " + currentUsername + "!", "Thành công",
                            JOptionPane.INFORMATION_MESSAGE);
                    resetField();
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Đổi mật khẩu thất bại! Vui lòng thử lại.", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void configurePasswordField(JPasswordField field, String title) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(150, 150, 150)),
                        title,
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        new Font("Segoe UI", Font.PLAIN, 12),
                        new Color(70, 70, 70)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        field.setBackground(new Color(255, 255, 255));
        field.setForeground(new Color(50, 50, 50));
    }

    private void configureButton(JButton button, Color bgColor, Color fgColor, String iconPath) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setPreferredSize(new Dimension(120, 40));
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
    }

    public void resetField() {
        txMatKhauCu.setText("");
        txMatKhauMoi.setText("");
        txXacNhanMatKhau.setText("");
    }

    private Boolean checkPass() {
        String mkcu = new String(txMatKhauCu.getPassword());
        String mkmoi = new String(txMatKhauMoi.getPassword());
        String xnmk = new String(txXacNhanMatKhau.getPassword());

        if (currentUser == null || !mkcu.equals(currentUser.getUserPassword())) {
            JOptionPane.showMessageDialog(this, "❌ Mật khẩu cũ không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txMatKhauCu.requestFocus();
            return false;
        } else if (mkmoi.isEmpty() || xnmk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Mật khẩu mới không được để trống!", "Cảnh báo",
                    JOptionPane.WARNING_MESSAGE);
            txMatKhauMoi.requestFocus();
            return false;
        } else if (!mkmoi.equals(xnmk)) {
            JOptionPane.showMessageDialog(this, "❌ Mật khẩu mới không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txXacNhanMatKhau.requestFocus();
            return false;
        }
        return true;
    }

    public static void showChangePasswordDialog(String username) {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(Application.getInstance()),
                "Đổi Mật Khẩu", true);
        DiaLogChangePass changePassPanel = new DiaLogChangePass(username);
        dialog.setContentPane(changePassPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false); // Prevent resizing for a consistent look
        dialog.setVisible(true);
    }
}