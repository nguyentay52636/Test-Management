package org.example.GUI.Components.FormMonHoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.example.GUI.Application.other.FormInbox;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelThemCauHoi;

public class JPanelGeneral extends JPanel {
    private JPanel contentPanel;

    public JPanelGeneral(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        initComponents();
    }

    private void initComponents() {
        // Cài đặt giao diện chung
        setBackground(new Color(34, 45, 65)); // Màu nền tối hiện đại
        setLayout(null); // Giữ layout null để dễ tùy chỉnh
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding xung quanh

        // Tiêu đề
        JLabel lblTitle = new JLabel("Quản Lý Câu Hỏi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitle.setForeground(new Color(240, 248, 255)); // Màu trắng nhạt
        lblTitle.setBounds(300, 20, 400, 40);
        add(lblTitle);

        // Chọn môn học
        JLabel lblSubject = new JLabel("Chọn Môn Học:");
        lblSubject.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubject.setForeground(Color.WHITE);
        lblSubject.setBounds(50, 80, 120, 25);
        add(lblSubject);

        JComboBox<String> cboSubject = new JComboBox<>(
                new String[] { "Mathematics", "Programming", "General Knowledge" });
        cboSubject.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboSubject.setBackground(new Color(60, 70, 90));
        cboSubject.setForeground(Color.WHITE);
        cboSubject.setBounds(50, 110, 200, 35);
        cboSubject.setBorder(BorderFactory.createLineBorder(new Color(100, 110, 130), 1));
        add(cboSubject);

        // Các nút chức năn
        JButton btnThem = createButton("Thêm", "/org/example/GUI/resources/images/plus.png", "Thêm câu hỏi mới");
        btnThem.setBounds(300, 110, 110, 45);
        add(btnThem);
        btnThem.addActionListener(e -> openAddQuestionPanel());

        JButton btnXoa = createButton("Xóa", "/org/example/GUI/resources/images/icons8_delete_30px_1.png",
                "Xóa câu hỏi đã chọn");
        btnXoa.setBounds(420, 110, 110, 45);
        add(btnXoa);

        JButton btnSua = createButton("Sửa", "/org/example/GUI/resources/images/icons8_wrench_30px.png",
                "Sửa câu hỏi hiện tại");
        btnSua.setBounds(540, 110, 110, 45);
        add(btnSua);
        // btnSua.addActionListener(e -> openEditQuestionPanel());

        JButton btnXuatExcel = createButton("Xuất Excel", "/org/example/GUI/resources/images/icons8_ms_excel_30px.png",
                "Xuất danh sách câu hỏi ra Excel");
        btnXuatExcel.setBounds(660, 110, 120, 45);
        add(btnXuatExcel);

        JButton btnNhapExcel = createButton("Nhập Excel", "org/example/GUI/resources/images/icons8_ms_excel_30px.png",
                "Nhập câu hỏi từ file Excel");
        btnNhapExcel.setBounds(790, 110, 120, 45);
        add(btnNhapExcel);

        // Bộ lọc và tìm kiếm
        JLabel lblLoc = new JLabel("Lọc Mức Độ:");
        lblLoc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblLoc.setForeground(Color.WHITE);
        lblLoc.setBounds(50, 170, 120, 25);
        add(lblLoc);

        JComboBox<String> cboLoc = new JComboBox<>(new String[] { "Tất cả", "Dễ", "Trung bình", "Khó" });
        cboLoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboLoc.setBackground(new Color(60, 70, 90));
        cboLoc.setForeground(Color.WHITE);
        cboLoc.setBounds(50, 200, 150, 35);
        cboLoc.setBorder(BorderFactory.createLineBorder(new Color(100, 110, 130), 1));
        add(cboLoc);

        JLabel lblMaCH = new JLabel("Tìm Mã CH:");
        lblMaCH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblMaCH.setForeground(Color.WHITE);
        lblMaCH.setBounds(220, 170, 120, 25);
        add(lblMaCH);

        JTextField txtMaCH = new JTextField();
        txtMaCH.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMaCH.setBackground(new Color(60, 70, 90));
        txtMaCH.setForeground(Color.WHITE);
        txtMaCH.setBorder(BorderFactory.createLineBorder(new Color(100, 110, 130), 1));
        txtMaCH.setBounds(220, 200, 150, 35);
        add(txtMaCH);

        JLabel lblCauHoi = new JLabel("Tìm Câu Hỏi:");
        lblCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCauHoi.setForeground(Color.WHITE);
        lblCauHoi.setBounds(390, 170, 120, 25);
        add(lblCauHoi);

        JTextField txtCauHoi = new JTextField();
        txtCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCauHoi.setBackground(new Color(60, 70, 90));
        txtCauHoi.setForeground(Color.WHITE);
        txtCauHoi.setBorder(BorderFactory.createLineBorder(new Color(100, 110, 130), 1));
        txtCauHoi.setBounds(390, 200, 250, 35);
        add(txtCauHoi);

        // Nút quay lại
        JButton btnBack = createButton("Quay Lại", null, "Trở về màn hình chính");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBack.setBounds(800, 600, 140, 45);
        btnBack.addActionListener(e -> returnToInbox());
        add(btnBack);
    }

    private JButton createButton(String text, String iconPath, String tooltip) {
        JButton button = new JButton(text);

        // Tải icon
        if (iconPath != null) {
            java.net.URL imgURL = getClass().getResource(iconPath);
            if (imgURL != null) {
                button.setIcon(new ImageIcon(imgURL));
            } else {
                System.out.println("Không tìm thấy icon: " + iconPath);
            }
        }

        // Thiết kế nút
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 90, 110)); // Màu nền nút
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 120, 140), 1));
        button.setToolTipText(tooltip);

        // Hiệu ứng hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(90, 110, 130)); // Màu khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 90, 110)); // Màu mặc định
            }
        });

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
            JPanel currentPanel = (JPanel) contentPanel.getComponent(0);
            JPanelThemCauHoi panelThemCauHoi = new JPanelThemCauHoi(contentPanel, currentPanel);
            contentPanel.removeAll();
            contentPanel.add(panelThemCauHoi);
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    // private void openEditQuestionPanel() {
    // if (contentPanel != null) {
    // JPanel currentPanel = (JPanel) contentPanel.getComponent(0);
    // JPanelSuaCauHoi suaCauHoiPanel = new JPanelSuaCauHoi(contentPanel,
    // currentPanel);
    // contentPanel.removeAll();
    // contentPanel.add(suaCauHoiPanel);
    // contentPanel.revalidate();
    // contentPanel.repaint();
    // System.out.println("Chuyển đến giao diện Sửa câu hỏi!");
    // }
    // }
}