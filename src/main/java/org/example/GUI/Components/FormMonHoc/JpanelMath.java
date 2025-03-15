package org.example.GUI.Components.FormMonHoc;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
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

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.example.BUS.QuestionBUS;
import org.example.DAO.AnswersDAO;
import org.example.DAO.QuestionDAO;
import org.example.DTO.QuestionDTO;
import org.example.GUI.Application.other.FormInbox;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelSuaCauHoi;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelThemCauHoi;
import org.example.GUI.FormDialog.DialogMonHoc.JPanelViewDetails;
import org.example.Utils.importExcel;

public class JpanelMath extends JPanel {
    private JPanel contentPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private QuestionDAO questionDAO;
    private QuestionBUS questionBUS;
    private JButton btnNhapExcel, btnExportDocx;
    private JTextField txtCauHoi;

    public JpanelMath(JPanel contentPanel) {
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
        JLabel lblTitle = new JLabel("Quản Lý Môn Toán");
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

        btnExportDocx = createButton("Export DOCX", "/org/example/GUI/resources/imageTopic/word_icon.png",
                "Xuất đề thi ra DOCX");
        btnExportDocx.setBounds(670, 80, 120, 45);
        btnExportDocx.addActionListener(e -> exportExamToDocx());
        add(btnExportDocx);

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
        cboLoc.addActionListener(e -> filterByLevel(cboLoc.getSelectedItem().toString()));
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
        add(txtCauHoi);

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
        btnViewDetails.addActionListener(e -> openViewDetailsPanel());
        add(btnViewDetails);

        // Table Title
        JLabel lblTableTitle = new JLabel("Danh Sách Câu Hỏi");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTableTitle.setForeground(Color.WHITE);
        lblTableTitle.setBounds(400, 220, 200, 30);
        add(lblTableTitle);

        // Question Table
        tableModel = new DefaultTableModel(new String[] { "Mã Câu Hỏi", "Nội Dung", "Mức độ", "Trạng Thái" }, 0);
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
        List<QuestionDTO> questions = questionDAO.getQuestionsByTopic(1);
        for (QuestionDTO q : questions) {
            tableModel.addRow(new Object[] {
                    q.getQuestionID(),
                    q.getQContent(),
                    q.getQLevel(),
                    q.getQStatus() ? "Hoạt động" : "Ẩn"
            });
        }
    }

    public void exportExamToDocx() {
        List<QuestionDTO> questions = questionDAO.getQuestionsByTopic(1); // Assuming topic 1 is English
        List<QuestionDTO> easyQs = new ArrayList<>();
        List<QuestionDTO> mediumQs = new ArrayList<>();
        List<QuestionDTO> difficultQs = new ArrayList<>();

        // Phân loại câu hỏi theo độ khó
        for (QuestionDTO q : questions) {
            switch (q.getQLevel()) {
                case "Dễ":
                    easyQs.add(q);
                    break;
                case "Trung bình":
                    mediumQs.add(q);
                    break;
                case "Khó":
                    difficultQs.add(q);
                    break;
            }
        }

        // Kiểm tra số lượng câu hỏi đủ hay không
        if (easyQs.size() < 5 || mediumQs.size() < 3 || difficultQs.size() < 2) {
            JOptionPane.showMessageDialog(this, "Không đủ câu hỏi cho mỗi mức độ (5 dễ, 3 trung bình, 2 khó)!");
            return;
        }

        // Chọn ngẫu nhiên các câu hỏi
        Collections.shuffle(easyQs);
        Collections.shuffle(mediumQs);
        Collections.shuffle(difficultQs);

        List<QuestionDTO> selectedQuestions = new ArrayList<>();
        selectedQuestions.addAll(easyQs.subList(0, 5));
        selectedQuestions.addAll(mediumQs.subList(0, 3));
        selectedQuestions.addAll(difficultQs.subList(0, 2));

        // Xuất ra file DOCX
        String filePath = "EnglishExam.docx";
        try (XWPFDocument document = new XWPFDocument()) {
            // Tiêu đề chính
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("ĐỀ THI TRẮC NGHIỆM");
            titleRun.setBold(true);
            titleRun.setFontSize(16);
            titleRun.addBreak(); // Xuống dòng

            int questionNumber = 1;
            for (QuestionDTO q : selectedQuestions) {
                // Câu hỏi
                XWPFParagraph questionPara = document.createParagraph();
                XWPFRun questionRun = questionPara.createRun();
                questionRun.setText(questionNumber + ". " + q.getQContent());
                questionRun.setBold(true);
                questionRun.addBreak();

                // Kiểm tra và thêm ảnh nếu có
                if (q.getQPicture() != null && !q.getQPicture().isEmpty()) {
                    try (java.io.InputStream imageStream = new FileInputStream(q.getQPicture())) {
                        XWPFParagraph imgPara = document.createParagraph();
                        XWPFRun imgRun = imgPara.createRun();
                        imgRun.addPicture(
                                imageStream,
                                XWPFDocument.PICTURE_TYPE_PNG,
                                q.getQPicture(),
                                300, 150);
                    } catch (Exception e) {
                        System.out
                                .println("Không thể chèn ảnh cho câu hỏi " + q.getQuestionID() + ": " + e.getMessage());
                        // Tiếp tục với các phần còn lại của tài liệu
                    }
                }

                // Đáp án mẫu (hard-coded như trong ví dụ của bạn)
                String[] options = {
                        "A. tay la la toi.",
                        "B. khong phai toi",
                        "C. là traidep nhat.",
                        "D. it gioi"
                };

                for (String option : options) {
                    XWPFParagraph optionPara = document.createParagraph();
                    optionPara.setIndentationLeft(400); // Thụt lề sang phải
                    XWPFRun optionRun = optionPara.createRun();
                    optionRun.setText(option);
                }

                questionNumber++;
            }

            // Ghi file
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out);
            }

            JOptionPane.showMessageDialog(this, "Đã xuất đề thi ra " + filePath + " thành công!");

            // Mở file sau khi xuất thành công
            File file = new File(filePath);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất DOCX: " + ex.getMessage());
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

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void filterByLevel(String level) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        if (!level.equals("Tất cả")) {
            sorter.setRowFilter(RowFilter.regexFilter(level, 2)); // Filter by "Mức độ" column
        } else {
            sorter.setRowFilter(null);
        }
    }

    private void returnToInbox() {
        if (contentPanel != null) {
            FormInbox formInbox = new FormInbox(contentPanel);
            contentPanel.removeAll();
            contentPanel.add(formInbox);
            contentPanel.revalidate();
            contentPanel.repaint();
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

    private void openEditQuestionPanel() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một câu hỏi để sửa!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int questionID = (int) tableModel.getValueAt(selectedRow, 0);
        QuestionDTO question = questionBUS.getQuestionByID(questionID);
        if (question == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy câu hỏi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JPanelSuaCauHoi suaCauHoiPanel = new JPanelSuaCauHoi(contentPanel, this, question, tableModel);
        contentPanel.removeAll();
        contentPanel.add(suaCauHoiPanel);
        contentPanel.revalidate();
        contentPanel.repaint();
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

    private void findQuestion() {
        String keyword = txtCauHoi.getText().trim().toLowerCase();
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        if (!keyword.isEmpty()) {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        } else {
            rowSorter.setRowFilter(null);
        }
    }
}