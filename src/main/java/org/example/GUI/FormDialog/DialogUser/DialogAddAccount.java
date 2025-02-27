package org.example.GUI.FormDialog.DialogUser;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

import org.example.DTO.UsersDTO;

public class DialogAddAccount extends JFrame {
    public interface AccountAddedListener {
        void onAccountAdded(UsersDTO newAccount);
    }

    public static AccountAddedListener accountAddedListener;

    String type;

    JTextField txUsername = new JTextField(15);
    JTextField txPassword = new JTextField(15);
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

    public void setDialogAddAccount(AccountAddedListener listener) {
        this.accountAddedListener = listener;
    }

    public DialogAddAccount(String _type, String _username) {

        // inputs
        txtEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        txUsername.setBorder(BorderFactory.createTitledBorder("Tên tài khoản"));
        txPassword.setBorder(BorderFactory.createTitledBorder("Mật khẩu"));
        txMaNV.setBorder(BorderFactory.createTitledBorder(" "));
        txMaQuyen.setBorder(BorderFactory.createTitledBorder(" "));

        JPanel plChonNhanVien = new JPanel();
        plChonNhanVien.setBorder(BorderFactory.createTitledBorder("Mã nhân viên"));
        plChonNhanVien.add(txMaNV);
        // plChonNhanVien.add(btnChonNhanVien);
        button1.setPreferredSize(new Dimension(50, 50));
        button1.setIcon(
                new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_assistant_30px.png")));
        plChonNhanVien.add(button1);

        JPanel plChonQuyen = new JPanel();
        plChonQuyen.setBorder(BorderFactory.createTitledBorder("Mã quyền"));
        plChonQuyen.add(txMaQuyen);
        // plChonQuyen.add(btnChonQuyen);
        button2.setPreferredSize(new Dimension(50, 50));
        button2.setIcon(new ImageIcon(
                getClass().getResource("/org/example/GUI/resources/images/icons8_police_badge_30px.png")));
        plChonQuyen.add(button2);

        JPanel plInput = new JPanel();
        plInput.add(txtEmail);
        plInput.add(txUsername);
        plInput.add(txPassword);
        plInput.add(plChonNhanVien);
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
            /*
             * this.setTitle("Sửa tài khoản");
             * for (Account tk : qltkBUS.getDstk()) {
             * if (tk.getUsername().equals(_username)) {
             * this.tkSua = tk;
             * }
             */

            // if (this.tkSua == null) {
            // JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy tài khoản");
            // this.dispose();
            // }
            // AccountBUS accountBUS = new AccountBUS();

            // txtEmail.setText(this.tkSua.getEmail());
            // String emailAdmin = "admin@example.com";
            // String getTxtEmail = txtEmail.getText();
            // if (getTxtEmail.equals(emailAdmin)) {
            // txtEmail.setEnabled(false); // Khóa ô input
            // } else {
            // txtEmail.setEnabled(true);
            // }
            //
            // txUsername.setText(this.tkSua.getUsername());
            // txUsername.setEditable(false);
            // txPassword.setText(this.tkSua.getPassword());
            // txMaNV.setText(this.tkSua.getMaNV());
            // txMaNV.setEnabled(false);
            // txMaQuyen.setText(this.tkSua.getMaQuyen());
            // txMaQuyen.setEnabled(false);
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
        // btnThem.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent actionEvent) {
        // btnThemMouseClicked();
        // }
        // });
        // btnSua.addActionListener((ae) -> {
        // btnSuaMouseClicked();
        // });
        // btnHuy.addActionListener((ae) -> {
        // this.dispose();
        // });
        // button1.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent actionEvent) {
        // FormChooseEmployee form = new FormChooseEmployee();
        // form.setVisible(true);
        // form.addButtonAddActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // String selectedEmployeeId = form.getSelectedEmployeeId();
        // if (selectedEmployeeId != null) {
        // txMaNV.setText(selectedEmployeeId);
        // }
        // }
        // });
        // }
        // });
        // button2.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent actionEvent) {
        // FormChoosePermission form = new FormChoosePermission();
        // form.setVisible(true);
        // form.addButtonAddActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent actionEvent) {
        // String selectedPermissionId = form.getSelectedPermissionId();
        // if (selectedPermissionId != null) {
        // txMaQuyen.setText(selectedPermissionId);
        // }
        // }
        // });
        // }
        // });

        this.setVisible(true);
    }

    // private void btnThemMouseClicked() {
    // if (checkEmpty()) {
    // String email = txtEmail.getText();
    // String username = txUsername.getText();
    // String pass = txPassword.getText();
    // String manv = txMaNV.getText();
    // txMaNV.setEnabled(false);
    // String maquyen = txMaQuyen.getText();
    // txMaQuyen.setEnabled(false);
    //
    // // Check if the email or username already exists
    // AccountBUS accountBUS = new AccountBUS();
    // boolean emailExists = false;
    // boolean usernameExists = false;
    //
    // for (Account account : accountBUS.getDstk()) {
    // if (account.getEmail().equals(email)) {
    // emailExists = true;
    // }
    // if (account.getUsername().equals(username)) {
    // usernameExists = true;
    // }
    // }
    //
    // if (emailExists) {
    // JOptionPane.showMessageDialog(this, "Email đã tồn tại!", "Thông báo",
    // JOptionPane.WARNING_MESSAGE);
    // } else if (usernameExists) {
    // JOptionPane.showMessageDialog(this, "Username đã tồn tại!", "Thông báo",
    // JOptionPane.WARNING_MESSAGE);
    // } else {
    // Account newAccount = new Account(email, username, pass, manv, maquyen);
    // if (qltkBUS.add(email, username, pass, manv, maquyen)) {
    // JOptionPane.showMessageDialog(this, "Thêm " + username + " thành công!");
    // if (accountAddedListener != null) {
    // accountAddedListener.onAccountAdded(newAccount);
    // }
    // this.dispose();
    // }
    // }
    // }
    // }

    // private void btnSuaMouseClicked() {
    // if (checkEmpty()) {
    // String email = txtEmail.getText();
    // String username = txUsername.getText();
    // String pass = txPassword.getText();
    // String manv = txMaNV.getText();
    // String maquyen = txMaQuyen.getText();
    // // Check if the email or username already exists
    // AccountBUS accountBUS = new AccountBUS();
    // boolean emailExists = false;
    // boolean usernameExists = false;
    //
    // for (Account account : accountBUS.getDstk()) {
    // if (account.getEmail().equals(email)) {
    // emailExists = true;
    // }
    // if (account.getUsername().equals(username)) {
    // usernameExists = true;
    // }
    // }
    //
    // if (emailExists) {
    // JOptionPane.showMessageDialog(this, "Email đã tồn tại!", "Thông báo",
    // JOptionPane.WARNING_MESSAGE);
    // } else if (usernameExists) {
    // JOptionPane.showMessageDialog(this, "Username đã tồn tại!", "Thông báo",
    // JOptionPane.WARNING_MESSAGE);
    // } else {
    // Account fixAccount = new Account(email, username, pass, manv, maquyen);
    // if (qltkBUS.update(email, username, pass, manv, maquyen)) {
    // JOptionPane.showMessageDialog(this, "Sửa " + username + " thành công!");
    // if (accountAddedListener != null) {
    // accountAddedListener.onAccountAdded(fixAccount);
    // }
    // this.dispose();
    // }
    // }
    //
    // }
    // }

    // private Boolean checkEmpty() {
    // String email = txtEmail.getText();
    // String username = txUsername.getText();
    // String pass = txPassword.getText();
    // String manv = txMaNV.getText();
    // String maquyen = txMaQuyen.getText();
    //
    // if (username.trim().equals("")) {
    // return showErrorTx(txUsername, "Tên đăng nhập không được để trống");
    //
    // } else if (email.trim().equals("")) {
    // return showErrorTx(txtEmail, "Email không được để trống");
    //
    // } else if (pass.equals("")) {
    // return showErrorTx(txPassword, "Mật khẩu không được để trống");
    //
    // } else if (manv.trim().equals("")) {
    // return showErrorTx(txMaNV, "Mã nhân viên không được để trống");
    //
    // } else if (maquyen.trim().equals("")) {
    // return showErrorTx(txMaQuyen, "Mã quyền không được để trống");
    // }
    //
    // return true;
    // }

    // private Boolean showErrorTx(JTextField tx, String errorInfo) {
    // JOptionPane.showMessageDialog(tx, errorInfo);
    // tx.requestFocus();
    // return false;
    // }

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
