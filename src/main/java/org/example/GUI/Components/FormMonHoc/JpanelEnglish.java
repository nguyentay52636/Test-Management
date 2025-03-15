package org.example.GUI.Components.FormMonHoc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.example.BUS.QuestionBUS;
import org.example.DAO.AnswersDAO;
import org.example.DAO.QuestionDAO;
import org.example.DTO.QuestionDTO;
import org.example.GUI.Application.other.FormInbox;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelSuaCauHoi;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelThemCauHoi;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelViewDetails;
import org.example.Utils.importExcel;

public class JpanelEnglish extends JPanel {
    private JPanel contentPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private QuestionDAO questionDAO;
    private QuestionBUS questionBUS;
    private JButton btnNhapExcel;
    private JTextField txtCauHoi;

    public JpanelEnglish(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        this.questionDAO = new QuestionDAO();
        this.questionBUS = new QuestionBUS();
        initComponents();
        loadDataFromDatabase();
    }

    private void initComponents() {
        setBackground(new Color(34, 45, 65));
        setLayout(null);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Title
        JLabel lblTitle = new JLabel("Quản Lý Môn Anh");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitle.setForeground(new Color(240, 248, 255));
        lblTitle.setBounds(350, 20, 400, 40);
        add(lblTitle);

        // Function Buttons
        JButton btnThem = createButton("Thêm", "/org/example/GUI/resources/images/plus.png", "Thêm câu hỏi mới");
        btnThem.setBounds(50, 80, 110, 45);
        btnThem.addActionListener(e -> openAddQuestionPanel());
        add(btnThem);

        JButton btnXoa = createButton("Xóa", "/org/example/GUI/resources/images/icons8_delete_forever_30px_1.png",
                "Xóa câu hỏi đã chọn");
        btnXoa.setBounds(170, 80, 110, 45);
        btnXoa.addActionListener(e -> deleteSelectedQuestion());
        add(btnXoa);

        JButton btnSua = createButton("Sửa", "/org/example/GUI/resources/images/icons8_wrench_30px.png",
                "Sửa câu hỏi hiện tại");
        btnSua.setBounds(290, 80, 110, 45);
        btnSua.addActionListener(e -> openEditQuestionPanel());
        add(btnSua);

        JButton btnXuatExcel = createButton("Xuất Excel", "/org/example/GUI/resources/images/icons8_ms_excel_30px.png",
                "Xuất danh sách câu hỏi ra Excel");
        btnXuatExcel.setBounds(410, 80, 120, 45);
        add(btnXuatExcel);

        btnNhapExcel = createButton("Nhập Excel", "/org/example/GUI/resources/images/icons8_ms_excel_30px.png",
                "Nhập câu hỏi từ file Excel");
        btnNhapExcel.setBounds(540, 80, 120, 45);
        btnNhapExcel.addActionListener(e -> importDataFromExcel());
        add(btnNhapExcel);

        // Filters and Search
        JLabel lblLoc = new JLabel("Lọc Mức Độ:");
        lblLoc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblLoc.setForeground(Color.WHITE);
        lblLoc.setBounds(50, 140, 120, 25);
        add(lblLoc);

        JComboBox<String> cboLoc = new JComboBox<>(new String[] { "Tất cả", "Dễ", "Trung bình", "Khó" });
        cboLoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboLoc.setBackground(new Color(60, 70, 90));
        cboLoc.setForeground(Color.WHITE);
        cboLoc.setBounds(50, 170, 150, 35);
        cboLoc.setBorder(BorderFactory.createLineBorder(new Color(100, 110, 130), 1));
        add(cboLoc);

        JLabel lblMaCH = new JLabel("Tìm Mã CH:");
        lblMaCH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblMaCH.setForeground(Color.WHITE);
        lblMaCH.setBounds(220, 140, 120, 25);
        add(lblMaCH);
        JLabel lblCauHoi = new JLabel("Tìm Câu Hỏi:");
        lblCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCauHoi.setForeground(Color.WHITE);
        lblCauHoi.setBounds(390, 140, 120, 25);
        add(lblCauHoi);
        JTextField txtMaCH = new JTextField();
        txtMaCH.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMaCH.setBackground(new Color(60, 70, 90));
        txtMaCH.setForeground(Color.WHITE);
        txtMaCH.setBorder(BorderFactory.createLineBorder(new Color(100, 110, 130), 1));
        txtMaCH.setBounds(220, 170, 150, 35);
        add(txtMaCH);

        txtCauHoi = new JTextField();
        txtCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCauHoi.setBackground(new Color(60, 70, 90));
        txtCauHoi.setForeground(Color.WHITE);
        txtCauHoi.setBorder(BorderFactory.createLineBorder(new Color(100, 110, 130), 1));
        txtCauHoi.setBounds(390, 170, 250, 35);
        add(txtCauHoi);
        txtCauHoi.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                findQuestion();
            }

            public void removeUpdate(DocumentEvent e) {
                findQuestion();
            }

            public void changedUpdate(DocumentEvent e) {
                findQuestion();
            }
        });

        // View Details Button
        JButton btnViewDetails = new JButton("Xem chi tiết");
        btnViewDetails.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnViewDetails.setBounds(650, 170, 150, 35);
        btnViewDetails.setBackground(new Color(70, 90, 110));
        btnViewDetails.setForeground(Color.WHITE);
        btnViewDetails.setBorder(BorderFactory.createLineBorder(new Color(100, 110, 130), 1));
        btnViewDetails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnViewDetails.setBackground(new Color(90, 110, 130));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnViewDetails.setBackground(new Color(70, 90, 110));
            }
        });
        // btnViewDetails.addActionListener(e -> {
        // int selectedRow = table.getSelectedRow();
        // if (selectedRow == -1) {
        // JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
        // "Vui lòng chọn một câu hỏi để xem chi tiết!", "Thông báo",
        // JOptionPane.WARNING_MESSAGE);
        // return;
        // }

        // int questionID = (int) tableModel.getValueAt(selectedRow, 0);
        // JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(this), "Chi
        // tiết câu hỏi", true);
        // JPanelViewDetails panelViewDetails = new JPanelViewDetails(contentPanel,
        // this, questionID);
        // dialog.setContentPane(panelViewDetails);
        // dialog.setSize(new Dimension(900, 550));
        // dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
        // dialog.setVisible(true);
        // });
        btnViewDetails.addActionListener(e -> openViewDetailsPanel());
        add(btnViewDetails);

        // Table Title
        JLabel lblTableTitle = new JLabel("Danh Sách Câu Hỏi");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTableTitle.setForeground(Color.WHITE);
        lblTableTitle.setBounds(400, 220, 200, 30);
        add(lblTableTitle);

        // Question Table
        tableModel = new DefaultTableModel(new String[] { "ID", "Nội dung", "Mức độ", "Trạng thái" }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 260, 900, 300);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(100, 110, 130), 1));
        add(scrollPane);

        // Back Button
        JButton btnBack = createButton("Quay Lại", null, "Trở về màn hình chính");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBack.setBounds(810, 570, 140, 45);
        btnBack.addActionListener(e -> returnToInbox());
        add(btnBack);
    }

    private void openViewDetailsPanel() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một câu hỏi để xem chi tiết!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int questionID = (int) tableModel.getValueAt(selectedRow, 0);
        JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(this), "Chi tiết câu hỏi", true);
        JPanelViewDetails panelViewDetails = new JPanelViewDetails(contentPanel, this, questionID);
        dialog.setContentPane(panelViewDetails);
        dialog.setSize(new Dimension(900, 550));
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private JButton createButton(String text, String iconPath, String tooltip) {
        JButton button = new JButton(text);
        if (iconPath != null) {
            java.net.URL imgURL = getClass().getResource(iconPath);
            if (imgURL != null) {
                button.setIcon(new ImageIcon(imgURL));
            } else {
                System.out.println("Không tìm thấy icon: " + iconPath);
            }
        }
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 90, 110));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 120, 140), 1));
        button.setToolTipText(tooltip);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(90, 110, 130));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 90, 110));
            }
        });
        return button;
    }

    private void loadDataFromDatabase() {
        tableModel.setRowCount(0);
        List<QuestionDTO> questions = questionDAO.getQuestionsByTopic(2); // Assuming topic ID 2 for History
        for (QuestionDTO q : questions) {
            tableModel.addRow(new Object[] {
                    q.getQuestionID(),
                    q.getQContent(),

                    q.getQLevel(),
                    q.getQStatus() ? "Hoạt động" : "Ẩn"
            });
        }
    }

    private void deleteSelectedQuestion() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một câu hỏi để xóa!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int questionID = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn xóa câu hỏi với ID " + questionID + " không?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            AnswersDAO answersDAO = new AnswersDAO();
            answersDAO.deleteAllAnswersByQuestionID(questionID);
            boolean deleted = questionBUS.deleteQuestion(questionID);
            if (deleted) {
                QuestionDAO questionDAO = new QuestionDAO();
                questionDAO.deleteQuestion(questionID);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Xóa câu hỏi thành công!", "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Xóa câu hỏi thất bại!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void importDataFromExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Chọn file Excel");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xlsx"));

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            List<QuestionDTO> questions = importExcel.readExcel(selectedFile.getAbsolutePath());
            for (QuestionDTO q : questions) {
                if (questionDAO.insertQuestion(q)) {
                    System.out.println("Thêm thành công: " + q.getQContent());
                } else {
                    System.out.println("Lỗi khi thêm: " + q.getQContent());
                }
            }
            setDataToTable(questions);
        }
    }

    public void setDataToTable(List<QuestionDTO> questions) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Câu Hỏi");
        model.addColumn("Nội Dung");
        model.addColumn("Cấp Độ");
        model.addColumn("Trạng Thái");

        for (QuestionDTO question : questions) {
            model.addRow(new Object[] {
                    question.getQuestionID(),
                    question.getQContent(),
                    question.getQLevel(),
                    question.getQStatus()
            });
        }

        table.setModel(model);

        // Căn giữa nội dung trong các cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Căn giữa

        // Áp dụng căn giữa cho tất cả các cột
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
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
            JPanelThemCauHoi panelThemCauHoi = new JPanelThemCauHoi(contentPanel, currentPanel, tableModel);
            contentPanel.removeAll();
            contentPanel.add(panelThemCauHoi);
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    private void findQuestion() {
        String keyword = txtCauHoi.getText().trim().toLowerCase();
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
        table.setRowSorter(rowSorter);

        if (!keyword.equals("")) {
            rowSorter.setRowFilter(new RowFilter<TableModel, Integer>() {
                @Override
                public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                    for (int i = 0; i < entry.getValueCount(); i++) {
                        if (entry.getStringValue(i).toLowerCase().contains(keyword)) {
                            return true; // Có ít nhất một trường khớp với từ khóa
                        }
                    }
                    return false; // Không có trường nào khớp
                }
            });
        } else {
            rowSorter.setRowFilter(null); // Nếu không nhập gì, hiển thị tất cả dữ liệu
        }
    }

    private void openEditQuestionPanel() { // Line ~284 from stack trace
        int selectedRow = table.getSelectedRow();
        JpanelEnglish currentPanel = (JpanelEnglish) contentPanel.getComponent(0);
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một câu hỏi để sửa!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int questionID = (int) tableModel.getValueAt(selectedRow, 0);
        QuestionDTO question = questionBUS.getQuestionByID(questionID);
        JPanelSuaCauHoi suaCauHoiPanel = new JPanelSuaCauHoi(contentPanel, currentPanel, question, tableModel);
        contentPanel.removeAll();
        contentPanel.add(suaCauHoiPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
        System.out.println("Chuyển đến giao diện Sửa câu hỏi!");// NPE here because questionBUS is null
        if (question == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy câu hỏi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Assuming this refreshes the table
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