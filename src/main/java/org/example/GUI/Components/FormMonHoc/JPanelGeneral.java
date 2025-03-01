package org.example.GUI.Components.FormMonHoc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.example.GUI.Application.other.FormInbox;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelSuaCauHoi;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelThemCauHoi;

public class JPanelGeneral extends JPanel {
    private JPanel contentPanel;

    public JPanelGeneral(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(45, 54, 69)); // Màu nền tối
        setLayout(null); // Để kéo thả tự do

        // Tiêu đề
        JLabel lblTitle = new JLabel("Quản Lý Câu Hỏi Tổng Hợp");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(329, 23, 400, 40);
        add(lblTitle);

        // Các nút chức năng
        JButton btnThem = createButton("Thêm",
                "/D:/Test-Management-main/src/main/java/org/example/GUI/resources/images/plus.png");
        btnThem.setBounds(50, 80, 100, 40);
        add(btnThem);
        btnThem.addActionListener(e -> openAddQuestionPanel());
        JButton btnXoa = createButton("Xóa",
                "/D:/Test-Management-main/src/main/java/org/example/GUI/resources/images/icons8_delete_30px_1.png");
        btnXoa.setBounds(160, 80, 100, 40);
        add(btnXoa);

        JButton btnSua = createButton("Sửa", "/icons/edit.png");
        btnSua.setBounds(270, 80, 100, 40);
        add(btnSua);
        btnSua.addActionListener(e -> openEditQuestionPanel());
        JButton btnXuatExcel = createButton("Xuất Excel", "/icons/excel.png");
        btnXuatExcel.setBounds(380, 80, 120, 40);
        add(btnXuatExcel);

        JButton btnNhapExcel = createButton("Nhập Excel", "/icons/excel.png");
        btnNhapExcel.setBounds(510, 80, 120, 40);
        add(btnNhapExcel);

        // Lọc câu hỏi
        JLabel lblLoc = new JLabel("Lọc mức độ dễ/khó");
        lblLoc.setForeground(Color.WHITE);
        lblLoc.setBounds(50, 140, 150, 25);
        add(lblLoc);

        JComboBox<String> cboLoc = new JComboBox<>(new String[] { "Tất cả", "Dễ", "Khó" });
        cboLoc.setBounds(50, 170, 120, 30);
        add(cboLoc);

        // Tìm kiếm mã CH
        JLabel lblMaCH = new JLabel("Tìm kiếm mã CH");
        lblMaCH.setForeground(Color.WHITE);
        lblMaCH.setBounds(200, 140, 150, 25);
        add(lblMaCH);

        JTextField txtMaCH = new JTextField();
        txtMaCH.setBounds(200, 170, 120, 30);
        add(txtMaCH);

        // Tìm kiếm câu hỏi
        JLabel lblCauHoi = new JLabel("Tìm câu hỏi");
        lblCauHoi.setForeground(Color.WHITE);
        lblCauHoi.setBounds(350, 140, 150, 25);
        add(lblCauHoi);

        JTextField txtCauHoi = new JTextField();
        txtCauHoi.setBounds(350, 170, 200, 30);
        add(txtCauHoi);
        JButton btnBack = new JButton("Quay lại");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
        btnBack.setBounds(793, 627, 150, 40); // Đặt vị trí và kích thước
        btnBack.addActionListener(e -> returnToInbox());
        add(btnBack);
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);

        // Sửa lỗi load icon
        java.net.URL imgURL = getClass().getResource(iconPath);
        if (imgURL != null) {
            button.setIcon(new ImageIcon(imgURL));
        } else {
            System.out.println("Không tìm thấy icon: " + iconPath);
        }

        button.setFocusPainted(false);
        button.setBackground(new Color(80, 100, 120));
        button.setForeground(Color.WHITE);
        return button;
    }

    private void returnToInbox() {
        if (contentPanel != null) {
            FormInbox formInbox = new FormInbox(contentPanel);
            contentPanel.removeAll();
            contentPanel.add(formInbox);
            contentPanel.revalidate();
            contentPanel.repaint();
            System.out.println("Quay lại FormInbox!");
        }
    }

    private void openAddQuestionPanel() {
        if (contentPanel != null) {
            JPanel currentPanel = (JPanel) contentPanel.getComponent(0); // Lưu panel hiện tại
            JPanelThemCauHoi panelThemCauHoi = new JPanelThemCauHoi(contentPanel, currentPanel);

            contentPanel.removeAll();
            contentPanel.add(panelThemCauHoi);
            contentPanel.revalidate();
            contentPanel.repaint();

        }
    }

    private void openEditQuestionPanel() {
        if (contentPanel != null) {
            JPanel currentPanel = (JPanel) contentPanel.getComponent(0);
            JPanelSuaCauHoi suaCauHoiPanel = new JPanelSuaCauHoi(contentPanel, currentPanel);
            contentPanel.removeAll();
            contentPanel.add(suaCauHoiPanel);
            contentPanel.revalidate();
            contentPanel.repaint();
            System.out.println("Chuyển đến giao diện Sửa câu hỏi!");
        }
    }
}
