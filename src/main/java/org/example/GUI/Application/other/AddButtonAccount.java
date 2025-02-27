package org.example.GUI.Application.other;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddButtonAccount extends JFrame {
    JTextField txUsername = new JTextField(15);
    JTextField txPassword = new JTextField(15);
    JTextField txMaNV = new JTextField(15);
    JTextField txMaQuyen = new JTextField(15);
    JTextField txtEmail = new JTextField(15);
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");

    public AddButtonAccount(String _type) {
        this.setLayout(new BorderLayout());
        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        txtEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        txUsername.setBorder(BorderFactory.createTitledBorder("Tên tài khoản"));
        txPassword.setBorder(BorderFactory.createTitledBorder("Mật khẩu"));
        txMaNV.setBorder(BorderFactory.createTitledBorder(" "));
        txMaQuyen.setBorder(BorderFactory.createTitledBorder(" "));

        JPanel plChonNhanVien = new JPanel();
        plChonNhanVien.setBorder(BorderFactory.createTitledBorder("Mã nhân viên"));
        plChonNhanVien.add(txMaNV);
        button1.setPreferredSize(new Dimension(50, 50));
        button1.setIcon(new ImageIcon(
                getClass().getResource("/org/example/GUI/resources/images/icons8_assistant_30px.png")));
        plChonNhanVien.add(button1);

        JPanel plChonQuyen = new JPanel();
        plChonQuyen.setBorder(BorderFactory.createTitledBorder("Mã quyền"));
        plChonQuyen.add(txMaQuyen);
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

        JPanel plButton = new JPanel();
        if (_type.equals("Thêm")) {
            this.setTitle("Thêm tài khoản");
            btnThem.setIcon(new ImageIcon(
                    this.getClass().getResource("/org/example/GUI/resources/images/icons8_add_30px.png")));
            plButton.add(btnThem);
        } else {
            this.setTitle("Sửa tài khoản");
            btnSua.setIcon(new ImageIcon(
                    this.getClass().getResource("/org/example/GUI/resources/images/icons8_support_30px.png")));
            plButton.add(btnSua);
        }
        btnHuy.setIcon(new ImageIcon(
                this.getClass().getResource("/org/example/GUI/resources/images/icons8_cancel_30px_1.png")));
        plButton.add(btnHuy);

        this.add(plInput, BorderLayout.CENTER);
        this.add(plButton, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
