package org.example.GUI.Components.FormMonHoc;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.example.DAO.QuestionDAO;
import org.example.DTO.QuestionDTO;
import org.example.GUI.Application.other.FormInbox;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelSuaCauHoi;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelThemCauHoi;
import org.example.Utils.importExcel;

public class JPanelAnh extends JPanel {
    private JPanel contentPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private QuestionDAO questionDAO;
    private JButton btnNhapExcel;

    public JPanelAnh(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        this.questionDAO = new QuestionDAO();
        initComponents();
        loadDataFromDatabase();
    }

    private void initComponents() {
        setBackground(new Color(45, 54, 69)); // Màu nền tối
        setLayout(null); // Để kéo thả tự do

        // Tiêu đề
        JLabel lblTitle = new JLabel("Quản Lý Môn Tiếng Anh");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(329, 23, 400, 40);
        add(lblTitle);

        // Bảng hiển thị dữ liệu câu hỏi
        tableModel = new DefaultTableModel(new String[] { "ID", "Nội dung", "Hình ảnh", "Mức độ", "Trạng thái" }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 276, 893, 300);
        add(scrollPane);

        // Các nút chức năng
        JButton btnThem = createButton("Thêm", "/icons/plus.png");
        btnThem.setBounds(50, 80, 100, 40);
        btnThem.addActionListener(e -> openAddQuestionPanel());
        add(btnThem);

        JButton btnXoa = createButton("Xóa", "/icons/delete.png");
        btnXoa.setBounds(160, 80, 100, 40);
        add(btnXoa);

        JButton btnSua = createButton("Sửa", "/icons/edit.png");
        btnSua.setBounds(270, 80, 100, 40);
        add(btnSua);
        btnSua.addActionListener(e -> openEditQuestionPanel());

        JButton btnXuatExcel = createButton("Xuất Excel", "/icons/excel.png");
        btnXuatExcel.setBounds(380, 80, 120, 40);
        add(btnXuatExcel);

        btnNhapExcel = createButton("Nhập Excel", "/icons/excel.png");
        btnNhapExcel.setBounds(510, 80, 120, 40);
        add(btnNhapExcel);
        btnNhapExcel.addActionListener(e -> importDataFromExcel());

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
        JLabel lblTableTitle = new JLabel("Danh Sách Câu Hỏi");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTableTitle.setForeground(Color.WHITE);
        lblTableTitle.setBounds(444, 226, 200, 30);
        add(lblTableTitle);
        // Tìm kiếm câu hỏi
        JLabel lblCauHoi = new JLabel("Tìm câu hỏi");
        lblCauHoi.setForeground(Color.WHITE);
        lblCauHoi.setBounds(350, 140, 150, 25);
        add(lblCauHoi);

        JTextField txtCauHoi = new JTextField();
        txtCauHoi.setBounds(350, 170, 200, 30);
        add(txtCauHoi);

        // Nút Quay lại
        JButton btnBack = new JButton("Quay lại");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
        btnBack.setBounds(765, 619, 178, 48);
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

    private void loadDataFromDatabase() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ

        List<QuestionDTO> questions = questionDAO.getQuestionsByTopic(1); // Chỉ lấy những câu hỏi có QTopicID = 1
        for (QuestionDTO q : questions) {
            tableModel.addRow(new Object[] {
                    q.getQuestionID(),
                    q.getQContent(),
                    q.getQPicture(),
                    q.getQLevel(),
                    q.getQStatus() ? "Hoạt động" : "Ẩn"
            });
        }
    }

    private void importDataFromExcel() {
        String filePath = "D:/tracnghiemAnh.xlsx"; // Đường dẫn file Excel

        // Đọc danh sách câu hỏi từ file Excel
        List<QuestionDTO> questions = importExcel.readExcel(filePath);
        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu trong file Excel!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lưu vào database
        for (QuestionDTO q : questions) {
            if (questionDAO.insertQuestion(q)) {
                System.out.println("Thêm thành công: " + q.getQContent());
            } else {
                System.out.println("Lỗi khi thêm: " + q.getQContent());
            }
        }

        // Cập nhật lại bảng
        loadDataFromDatabase();
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
