package org.example.GUI.FormDialog.DialogUser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

import org.example.BUS.UserBUS;
import org.example.DTO.UsersDTO;
import org.example.GUI.Components.FormAccount.FormManagerAccount;

public class DialogAddAccount extends JFrame {
    UserBUS qltkBUS = new UserBUS();
    UsersDTO tkSua;
    // private FormManagerAccount.AccountAddedListener accountAddedListener;

    // public interface AccountAddedListener {
    // void onAccountAdded(UsersDTO newAccount);
    // }

    private FormManagerAccount.AccountAddedListener accountAddedListener;

    private String type; // Initialize the type field

    JTextField txUsername = new JTextField(15);
    JTextField txPassword = new JTextField(15);
    JTextField txtFullName = new JTextField(15);
    JTextField txMaNV = new JTextField(15);
    JTextField txMaQuyen = new JTextField(15);
    JTextField txtEmail = new JTextField(15);
    // MoreButton btnChonNhanVien = new MoreButton();
    // MoreButton btnChonQuyen = new MoreButton();
    JButton button1 = new JButton();
    JButton button2 = new JButton();

    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");
    JComboBox<String> roleComboBox = new JComboBox<>(new String[] { "Admin", "User" });

    // public void setDialogAddAccount(AccountAddedListener listener) {
    // DialogAddAccount.accountAddedListener = listener;
    // }

    public void setAccountAddedListener(FormManagerAccount.AccountAddedListener listener) {
        this.accountAddedListener = listener;
    }

    public DialogAddAccount(String _type, String _username) {
        this.type = _type; // Initialize the type field

        // inputs
        txtEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        txUsername.setBorder(BorderFactory.createTitledBorder("Tên tài khoản"));
        txPassword.setBorder(BorderFactory.createTitledBorder("Mật khẩu"));
        txtFullName.setBorder(BorderFactory.createTitledBorder("Tên đầy đủ "));
        txMaQuyen.setBorder(BorderFactory.createTitledBorder(" "));

        // plChonNhanVien.setBorder(BorderFactory.createTitledBorder("Mã nhân viên"));
        // plChonNhanVien.add(txMaNV);
        // plChonNhanVien.add(btnChonNhanVien);
        // button1.setPreferredSize(new Dimension(50, 50));
        // button1.setIcon(
        // new
        // ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_assistant_30px.png")));
        // plChonNhanVien.add(button1);

        JPanel plChonQuyen = new JPanel();
        plChonQuyen.setBorder(BorderFactory.createTitledBorder("Mã quyền"));
        plChonQuyen.add(roleComboBox);
        // plChonQuyen.add(btnChonQuyen);
        // button2.setPreferredSize(new Dimension(50, 50));
        // button2.setIcon(new ImageIcon(
        // getClass().getResource("/org/example/GUI/resources/images/icons8_police_badge_30px.png")));
        // plChonQuyen.add(button2);

        JPanel plInput = new JPanel();
        plInput.add(txtEmail);
        plInput.add(txUsername);
        plInput.add(txtFullName);
        plInput.add(txPassword);
        // plInput.add(plChonNhanVien);
        plInput.add(plChonQuyen);

        // panel buttons
        JPanel plButton = new JPanel();

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm tài khoản");

            btnThem.setIcon(new ImageIcon(
                    this.getClass().getResource("/org/example/GUI/resources/images/icons8_add_30px.png")));
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa tài khoản");
            // Iterate over accounts and find the matching one
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
            // Set text fields using tkSua
            txtEmail.setText(this.tkSua.getUserEmail());
            txUsername.setText(this.tkSua.getUserName());
            // txUsername.setEditable(false);
            txtFullName.setText(this.tkSua.getUserFullName());
            txPassword.setText(this.tkSua.getUserPassword());
            roleComboBox.setSelectedItem(this.tkSua.getIsAdmin() ? "Admin" : "User");
            btnSua.setIcon(new ImageIcon(
                    this.getClass().getResource("/org/example/GUI/resources/images/icons8_support_30px.png")));
            plButton.add(btnSua);
        }
        btnHuy.setIcon(new ImageIcon(
                this.getClass().getResource("/org/example/GUI/resources/images/icons8_cancel_30px_1.png")));
        plButton.add(btnHuy);

        this.add(plInput, BorderLayout.CENTER);
        this.add(plButton, BorderLayout.SOUTH);

        // mouse listener
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btnThemMouseClicked();
            }
        });
        btnSua.addActionListener((ae) -> {
            btnSuaMouseClicked();
        });
        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });

        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            String email = txtEmail.getText();
            String username = txUsername.getText();
            String fullName = txtFullName.getText();
            String pass = txPassword.getText();
            String maquyen = roleComboBox.getSelectedItem().toString();

            // Chuyển đổi maquyen từ String sang Boolean
            Boolean isAdmin = "Admin".equalsIgnoreCase(maquyen);

            // Kiểm tra email và username đã tồn tại chưa
            UserBUS accountBUS = new UserBUS();
            boolean emailExists = false;
            boolean usernameExists = false;
            int maxId = 0; // Để lưu userID lớn nhất hiện có

            for (UsersDTO account : accountBUS.getListAccount()) {
                if (account.getUserEmail().equals(email)) {
                    emailExists = true;
                }
                if (account.getUserName().equals(username)) {
                    usernameExists = true;
                }
                // Tìm userID lớn nhất
                if (account.getUserID() > maxId) {
                    maxId = account.getUserID();
                }
            }

            if (emailExists) {
                JOptionPane.showMessageDialog(this, "Email đã tồn tại!", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            } else if (usernameExists) {
                JOptionPane.showMessageDialog(this, "Username đã tồn tại!", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int newUserId = maxId + 1;
                UsersDTO newAccount = new UsersDTO(newUserId, username, email, pass, fullName, isAdmin);

                // Giả sử qltkBUS.add() thêm tài khoản vào db thành công
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
            System.out.println(maquyen);

            Boolean isAdmin = "Admin".equalsIgnoreCase(maquyen);
            int UserID = 0;
            if (tkSua != null) {
                UserID = tkSua.getUserID();
            }

            // ...any other validation code...

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

        if (fullName.trim().equals("")) {
            return showErrorTx(txtFullName, "Tên đầy đủ không được để trống");
        } else if (username.trim().equals("")) {
            return showErrorTx(txUsername, "Tên đăng nhập không được để trống");

        } else if (email.trim().equals("")) {
            return showErrorTx(txtEmail, "Email không được để trống");

        } else if (pass.equals("")) {
            return showErrorTx(txPassword, "Mật khẩu không được để trống");

            // } else if (maquyen.trim().equals("")) {
            // return showErrorTx(txMaQuyen, "Mã quyền không được để trống");
        }

        return true;
    }

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                .getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DialogAddAccount("Thêm", "master");
            }
        });
    }
}
