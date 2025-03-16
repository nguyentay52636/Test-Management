package org.example.GUI.Components.FormMonHoc;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import org.example.GUI.Application.other.FormManagerTopic;
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
    private JButton btnNhapExcel, btnExportDocx;
    private JTextField txtCauHoi;

    public JpanelEnglish(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        this.questionDAO = new QuestionDAO();
        this.questionBUS = new QuestionBUS();
        initComponents();
        loadDataFromDatabase();
    }

    private void initComponents() {
        setBackground(new Color(245, 248, 252));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel lblTitle = new JLabel("Quản Lý Môn Anh");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(new Color(33, 37, 41));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitle, gbc);

        // Function Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setBackground(new Color(245, 248, 252));
        Dimension buttonSize = new Dimension(120, 40);

        JButton btnThem = createButton("Thêm", "/org/example/GUI/resources/images/plus.png", "Thêm câu hỏi mới",
                e -> openAddQuestionPanel());
        btnThem.setPreferredSize(buttonSize);
        buttonPanel.add(btnThem);

        JButton btnXoa = createButton("Xóa", "/org/example/GUI/resources/images/icons8_delete_forever_30px_1.png",
                "Xóa câu hỏi đã chọn", e -> deleteSelectedQuestion());
        btnXoa.setPreferredSize(buttonSize);
        buttonPanel.add(btnXoa);

        JButton btnSua = createButton("Sửa", "/org/example/GUI/resources/images/icons8_wrench_30px.png",
                "Sửa câu hỏi hiện tại", e -> openEditQuestionPanel());
        btnSua.setPreferredSize(buttonSize);
        buttonPanel.add(btnSua);

        JButton btnXuatExcel = createButton("Xuất Excel", "/org/example/GUI/resources/images/icons8_ms_excel_30px.png",
                "Xuất danh sách câu hỏi ra Excel", null);
        btnXuatExcel.setPreferredSize(buttonSize);
        buttonPanel.add(btnXuatExcel);

        btnNhapExcel = createButton("Nhập Excel", "/org/example/GUI/resources/images/icons8_ms_excel_30px.png",
                "Nhập câu hỏi từ file Excel", e -> importDataFromExcel());
        btnNhapExcel.setPreferredSize(buttonSize);
        buttonPanel.add(btnNhapExcel);

        btnExportDocx = createButton("Export DOCX", "/org/example/GUI/resources/imageTopic/word_icon.png",
                "Xuất đề thi ra DOCX", e -> exportExamToDocx());
        btnExportDocx.setPreferredSize(buttonSize);
        buttonPanel.add(btnExportDocx);

        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);

        // Filters and Search
        JPanel filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBackground(new Color(245, 248, 252));
        GridBagConstraints filterGbc = new GridBagConstraints();
        filterGbc.insets = new Insets(5, 10, 5, 10);

        JLabel lblLoc = new JLabel("Lọc Mức Độ:");
        lblLoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblLoc.setForeground(new Color(66, 80, 102));
        filterGbc.gridx = 0;
        filterGbc.gridy = 0;
        filterPanel.add(lblLoc, filterGbc);

        JComboBox<String> cboLoc = new JComboBox<>(new String[] { "Tất cả", "Dễ", "Trung bình", "Khó" });
        cboLoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboLoc.setBackground(Color.WHITE);
        cboLoc.setPreferredSize(new Dimension(150, 35));
        cboLoc.addActionListener(e -> filterByLevel(cboLoc.getSelectedItem().toString()));
        filterGbc.gridy = 1;
        filterPanel.add(cboLoc, filterGbc);

        JLabel lblMaCH = new JLabel("Tìm Mã CH:");
        lblMaCH.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMaCH.setForeground(new Color(66, 80, 102));
        filterGbc.gridx = 1;
        filterGbc.gridy = 0;
        filterPanel.add(lblMaCH, filterGbc);

        JTextField txtMaCH = new JTextField();
        txtMaCH.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMaCH.setPreferredSize(new Dimension(150, 35));
        filterGbc.gridy = 1;
        filterPanel.add(txtMaCH, filterGbc);

        JLabel lblCauHoi = new JLabel("Tìm Câu Hỏi:");
        lblCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCauHoi.setForeground(new Color(66, 80, 102));
        filterGbc.gridx = 2;
        filterGbc.gridy = 0;
        filterPanel.add(lblCauHoi, filterGbc);

        txtCauHoi = new JTextField();
        txtCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCauHoi.setPreferredSize(new Dimension(250, 35));
        txtCauHoi.getDocument().addDocumentListener(new SimpleDocumentListener(this::findQuestion));
        filterGbc.gridy = 1;
        filterPanel.add(txtCauHoi, filterGbc);

        JButton btnViewDetails = createButton("Xem chi tiết", null, "Xem chi tiết câu hỏi",
                e -> openViewDetailsPanel());
        btnViewDetails.setPreferredSize(new Dimension(120, 35));
        filterGbc.gridx = 3;
        filterGbc.gridy = 1;
        filterPanel.add(btnViewDetails, filterGbc);

        gbc.gridy = 2;
        gbc.gridwidth = 6;
        add(filterPanel, gbc);

        // Table Title
        JLabel lblTableTitle = new JLabel("Danh Sách Câu Hỏi");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTableTitle.setForeground(new Color(33, 37, 41));
        gbc.gridy = 3;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblTableTitle, gbc);

        // Question Table
        tableModel = new DefaultTableModel(new String[] { "ID", "Nội dung", "Mức độ", "Trạng thái" }, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(52, 108, 176));
        table.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridy = 4;
        gbc.gridwidth = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Back Button
        JButton btnBack = createButton("Quay Lại", null, "Trở về màn hình chính", e -> returnToInbox());
        btnBack.setPreferredSize(new Dimension(120, 40));
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnBack, gbc);
    }

    private JButton createButton(String text, String iconPath, String tooltip, java.awt.event.ActionListener action) {
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
        button.setBackground(new Color(52, 108, 176));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 86, 140), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        button.setToolTipText(tooltip);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(72, 128, 196));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(52, 108, 176));
            }
        });
        if (action != null) {
            button.addActionListener(action);
        }
        return button;
    }

    private void loadDataFromDatabase() {
        tableModel.setRowCount(0);
        List<QuestionDTO> questions = questionDAO.getQuestionsByTopic(2); // Topic ID 2 for English
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
        List<QuestionDTO> questions = questionDAO.getQuestionsByTopic(2);
        List<QuestionDTO> easyQs = new ArrayList<>();
        List<QuestionDTO> mediumQs = new ArrayList<>();
        List<QuestionDTO> difficultQs = new ArrayList<>();

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

        if (easyQs.size() < 5 || mediumQs.size() < 3 || difficultQs.size() < 2) {
            JOptionPane.showMessageDialog(this, "Không đủ câu hỏi cho mỗi mức độ (5 dễ, 3 trung bình, 2 khó)!");
            return;
        }

        Collections.shuffle(easyQs);
        Collections.shuffle(mediumQs);
        Collections.shuffle(difficultQs);

        List<QuestionDTO> selectedQuestions = new ArrayList<>();
        selectedQuestions.addAll(easyQs.subList(0, 5));
        selectedQuestions.addAll(mediumQs.subList(0, 3));
        selectedQuestions.addAll(difficultQs.subList(0, 2));

        String filePath = "EnglishExam.docx";
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("ĐỀ THI TRẮC NGHIỆM MÔN ANH");
            titleRun.setBold(true);
            titleRun.setFontSize(16);
            titleRun.addBreak();

            int questionNumber = 1;
            for (QuestionDTO q : selectedQuestions) {
                XWPFParagraph questionPara = document.createParagraph();
                XWPFRun questionRun = questionPara.createRun();
                questionRun.setText(questionNumber + ". " + q.getQContent());
                questionRun.setBold(true);
                questionRun.addBreak();

                if (q.getQPicture() != null && !q.getQPicture().isEmpty()) {
                    try (FileInputStream imageStream = new FileInputStream(q.getQPicture())) {
                        XWPFParagraph imgPara = document.createParagraph();
                        XWPFRun imgRun = imgPara.createRun();
                        imgRun.addPicture(imageStream, XWPFDocument.PICTURE_TYPE_PNG, q.getQPicture(), 300, 150);
                    } catch (Exception e) {
                        System.out
                                .println("Không thể chèn ảnh cho câu hỏi " + q.getQuestionID() + ": " + e.getMessage());
                    }
                }

                String[] options = { "A. tay la la toi.", "B. khong phai toi", "C. là traidep nhat.", "D. it gioi" };
                for (String option : options) {
                    XWPFParagraph optionPara = document.createParagraph();
                    optionPara.setIndentationLeft(400);
                    XWPFRun optionRun = optionPara.createRun();
                    optionRun.setText(option);
                }
                questionNumber++;
            }

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out);
            }

            JOptionPane.showMessageDialog(this, "Đã xuất đề thi ra " + filePath + " thành công!");
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
            sorter.setRowFilter(RowFilter.regexFilter(level, 2));
        } else {
            sorter.setRowFilter(null);
        }
    }

    private void returnToInbox() {
        if (contentPanel != null) {
            FormManagerTopic formInbox = new FormManagerTopic(contentPanel);
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
                JOptionPane.showMessageDialog(this, "Xóa câu hỏi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    private static class SimpleDocumentListener implements DocumentListener {
        private final Runnable action;

        public SimpleDocumentListener(Runnable action) {
            this.action = action;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            action.run();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            action.run();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            action.run();
        }
    }
}