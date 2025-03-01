package org.example.GUI.FormDialog.DialogMonHoc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPanelThemCauHoi extends JPanel {
    private JPanel contentPanel;
    private JPanel previousPanel;

    public JPanelThemCauHoi(JPanel contentPanel, JPanel previousPanel) {
        this.contentPanel = contentPanel;
        this.previousPanel = previousPanel; // Lưu panel trước đó
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(30, 30, 30));
        setLayout(null);
        setPreferredSize(new Dimension(900, 500)); // Phóng to hơn

        // Tiêu đề
        JLabel lblTitle = new JLabel("Thêm câu hỏi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Tăng font chữ
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(350, 10, 300, 30); // Căn giữa tiêu đề
        add(lblTitle);

        // ID Câu hỏi
        JLabel lblID = new JLabel("ID Câu hỏi");
        lblID.setForeground(Color.WHITE);
        lblID.setBounds(50, 60, 100, 25);
        add(lblID);

        JTextField txtID = new JTextField();
        txtID.setBounds(150, 60, 200, 30);
        add(txtID);

        // Loại câu hỏi (ComboBox)
        JLabel lblLoai = new JLabel("Loại câu hỏi");
        lblLoai.setForeground(Color.WHITE);
        lblLoai.setBounds(400, 60, 100, 25);
        add(lblLoai);

        JComboBox<String> cboLoai = new JComboBox<>(new String[] { "Dễ", "Khó" });
        cboLoai.setBounds(500, 60, 100, 30);
        add(cboLoai);

        // Nhập câu hỏi
        JLabel lblCauHoi = new JLabel("Câu hỏi");
        lblCauHoi.setForeground(Color.WHITE);
        lblCauHoi.setBounds(50, 110, 100, 25);
        add(lblCauHoi);

        JTextField txtCauHoi = new JTextField();
        txtCauHoi.setBounds(150, 110, 600, 30);
        txtCauHoi.setBackground(Color.LIGHT_GRAY);
        add(txtCauHoi);

        // Nhập đáp án
        JLabel lblDapAn = new JLabel("Đáp Án");
        lblDapAn.setForeground(Color.WHITE);
        lblDapAn.setBounds(50, 160, 100, 25);
        add(lblDapAn);

        JCheckBox chkA = new JCheckBox("A");
        chkA.setBounds(80, 190, 50, 25);
        add(chkA);
        JTextField txtA = new JTextField();
        txtA.setBounds(130, 190, 600, 30);
        add(txtA);

        JCheckBox chkB = new JCheckBox("B");
        chkB.setBounds(80, 230, 50, 25);
        add(chkB);
        JTextField txtB = new JTextField();
        txtB.setBounds(130, 230, 600, 30);
        add(txtB);

        JCheckBox chkC = new JCheckBox("C");
        chkC.setBounds(80, 270, 50, 25);
        add(chkC);
        JTextField txtC = new JTextField();
        txtC.setBounds(130, 270, 600, 30);
        add(txtC);

        JCheckBox chkD = new JCheckBox("D");
        chkD.setBounds(80, 310, 50, 25);
        add(chkD);
        JTextField txtD = new JTextField();
        txtD.setBounds(130, 310, 600, 30);
        add(txtD);

        // Nút thoát
        JButton btnThoat = new JButton("Thoát");
        btnThoat.setBounds(250, 380, 150, 40); // Tăng kích thước
        btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnThoat.addActionListener(e -> returnToMain());
        add(btnThoat);

        // Nút chấp nhận
        JButton btnChapNhan = new JButton("Chấp nhận");
        btnChapNhan.setBounds(500, 380, 150, 40);
        btnChapNhan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnChapNhan.setBackground(Color.RED);
        btnChapNhan.setForeground(Color.WHITE);
        add(btnChapNhan);
    }

    private void returnToMain() {
        if (contentPanel != null && previousPanel != null) {
            contentPanel.removeAll();
            contentPanel.add(previousPanel); // Quay về panel trước đó
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

}
