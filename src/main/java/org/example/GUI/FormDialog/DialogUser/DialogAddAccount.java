package org.example.GUI.FormDialog.DialogUser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import org.example.BUS.UserBUS;
import org.example.DTO.UsersDTO;
import org.example.GUI.Components.FormAccount.FormManagerAccount;

public class DialogAddAccount extends JFrame {
    UserBUS qltkBUS = new UserBUS();
    UsersDTO tkSua;
    private FormManagerAccount.AccountAddedListener accountAddedListener;
    private String type;

    JTextField txUsername = new JTextField(15);
    JTextField txPassword = new JTextField(15);
    JTextField txtFullName = new JTextField(15);
    JTextField txMaNV = new JTextField(15);
    JTextField txMaQuyen = new JTextField(15);
    JTextField txtEmail = new JTextField(15);
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");
    JComboBox<String> roleComboBox = new JComboBox<>(new String[] { "Admin", "User" });

    public DialogAddAccount(String _type, String _username) {
        this.type = _type;
        initComponents(_username);
    }

    public void setAccountAddedListener(FormManagerAccount.AccountAddedListener listener) {
        this.accountAddedListener = listener;
    }

    private void initComponents(String _username) {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 245)); // Nền nhẹ nhàng

        // Header Panel với gradient
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(52, 152, 219), getWidth(), 0, new Color(41, 128, 185));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 60));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        JLabel titleLabel = new JLabel(type.equals("Thêm") ? "Thêm Tài Khoản" : "Sửa Tài Khoản");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Input Panel
        JPanel plInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        plInput.setOpaque(false);
        plInput.setBorder(new EmptyBorder(10, 10, 10, 10));

        txtEmail = createStyledTextField("Email");
        txUsername = createStyledTextField("Tên tài khoản");
        txPassword = createStyledTextField("Mật khẩu");
        txtFullName = createStyledTextField("Tên đầy đủ");
        JPanel plChonQuyen = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        plChonQuyen.setOpaque(false);
        plChonQuyen.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)), "Mã quyền",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.PLAIN, 12)));
        roleComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roleComboBox.setPreferredSize(new Dimension(150, 40));
        plChonQuyen.add(roleComboBox);

        plInput.add(txtEmail);
        plInput.add(txUsername);
        plInput.add(txtFullName);
        plInput.add(txPassword);
        plInput.add(plChonQuyen);
        add(plInput, BorderLayout.CENTER);

        // Button Panel
        JPanel plButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        plButton.setOpaque(false);
        if (type.equals("Thêm")) {
            btnThem = createStyledButton("Thêm", new Color(46, 204, 113), 
                    new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_add_30px.png")));
            plButton.add(btnThem);
        } else {
            for (UsersDTO tk : qltkBUS.getListAccount()) {
                if (tk.getUserName().equals(_username)) {
                    this.tkSua = tk;
                    break;
                }
            }
            if (this.tkSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy tài khoản");
                this.dispose();
                return;
            }
            txtEmail.setText(this.tkSua.getUserEmail());
            txUsername.setText(this.tkSua.getUserName());
            txtFullName.setText(this.tkSua.getUserFullName());
            txPassword.setText(this.tkSua.getUserPassword());
            roleComboBox.setSelectedItem(this.tkSua.getIsAdmin() ? "Admin" : "User");
            btnSua = createStyledButton("Sửa", new Color(52, 152, 219), 
                    new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_support_30px.png")));
            plButton.add(btnSua);
        }
        btnHuy = createStyledButton("Hủy", new Color(231, 76, 60), 
                new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_cancel_30px_1.png")));
        plButton.add(btnHuy);
        add(plButton, BorderLayout.SOUTH);

        // Event Listeners
        btnThem.addActionListener(e -> btnThemMouseClicked());
        btnSua.addActionListener(e -> btnSuaMouseClicked());
        btnHuy.addActionListener(e -> this.dispose());

        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor, ImageIcon icon) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(new EmptyBorder(5, 10, 5, 10));
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

    private JTextField createStyledTextField(String title) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(180, 50));
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)), title,
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.PLAIN, 12)));
        return textField;
    }

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            String email = txtEmail.getText();
            String username = txUsername.getText();
            String fullName = txtFullName.getText();
            String pass = txPassword.getText();
            String maquyen = roleComboBox.getSelectedItem().toString();
            Boolean isAdmin = "Admin".equalsIgnoreCase(maquyen);

            UserBUS accountBUS = new UserBUS();
            boolean emailExists = false;
            boolean usernameExists = false;
            int maxId = 0;

            for (UsersDTO account : accountBUS.getListAccount()) {
                if (account.getUserEmail().equals(email)) emailExists = true;
                if (account.getUserName().equals(username)) usernameExists = true;
                if (account.getUserID() > maxId) maxId = account.getUserID();
            }

            if (emailExists) {
                JOptionPane.showMessageDialog(this, "Email đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else if (usernameExists) {
                JOptionPane.showMessageDialog(this, "Username đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
                int newUserId = maxId + 1;
                UsersDTO newAccount = new UsersDTO(newUserId, username, email, pass, fullName, isAdmin);
                if (qltkBUS.insertUser(newAccount)) {
                    JOptionPane.showMessageDialog(this, "Thêm " + username + " thành công!");
                    if (accountAddedListener != null) {
                        accountAddedListener.onAccountAdded(newAccount);
                    }
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm " + username + " thất bại!");
                }
            }
        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String email = txtEmail.getText();
            String username = txUsername.getText();
            String fullName = txtFullName.getText();
            String pass = txPassword.getText();
            String maquyen = roleComboBox.getSelectedItem().toString();
            Boolean isAdmin = "Admin".equalsIgnoreCase(maquyen);
            int UserID = tkSua != null ? tkSua.getUserID() : 0;

            UsersDTO newAccount = new UsersDTO(UserID, username, email, pass, fullName, isAdmin);
            if (qltkBUS.updateUser(newAccount)) {
                JOptionPane.showMessageDialog(this, "Sửa " + username + " thành công!");
                if (accountAddedListener != null) {
                    accountAddedListener.onAccountAdded(newAccount);
                }
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String email = txtEmail.getText();
        String username = txUsername.getText();
        String pass = txPassword.getText();
        String fullName = txtFullName.getText();

        if (fullName.trim().isEmpty()) return showErrorTx(txtFullName, "Tên đầy đủ không được để trống");
        if (username.trim().isEmpty()) return showErrorTx(txUsername, "Tên đăng nhập không được để trống");
        if (email.trim().isEmpty()) return showErrorTx(txtEmail, "Email không được để trống");
        if (pass.isEmpty()) return showErrorTx(txPassword, "Mật khẩu không được để trống");

        return true;
    }

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        javax.swing.SwingUtilities.invokeLater(() -> new DialogAddAccount("Thêm", "master"));
    }
}