package org.example.GUI.FormDialog.DialogTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.BUS.AnswersBUS;
import org.example.BUS.ExamBUS;
import org.example.BUS.QuestionBUS;
import org.example.DTO.AnswersDTO;
import org.example.DTO.ExamsDTO;
import org.example.DTO.QuestionDTO;
import org.example.GUI.Components.FormTest.TestManagementPanel;

public class DialogViewDetailTest extends JDialog {
    private TestManagementPanel parentPanel;
    private TestManagementPanel.Exam exam;
    private List<TestManagementPanel.Exam> examList;
    private JTable questionTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton btnImport, btnExport, btnSearch;
    private List<String> questionIDs;
    private ExamBUS examBUS;
    private QuestionBUS questionBUS;
    private AnswersBUS answersBUS;

    public DialogViewDetailTest(TestManagementPanel parentPanel, TestManagementPanel.Exam exam,
            List<TestManagementPanel.Exam> examList) {
        super((Frame) SwingUtilities.getWindowAncestor(parentPanel), "Chi Tiết Kỳ Thi: " + exam.getTitle(), true);
        this.parentPanel = parentPanel;
        this.exam = exam;
        this.examList = examList;
        this.examBUS = new ExamBUS();
        this.questionBUS = new QuestionBUS();
        this.answersBUS = new AnswersBUS();
        this.questionIDs = new ArrayList<>();
        setLayout(new BorderLayout(10, 10));
        setSize(600, 400);
        setLocationRelativeTo(parentPanel);
        loadQuestionData();
        initializeUI();
    }

    private void loadQuestionData() {
        try {
            ExamsDTO examDTO = examBUS.getExamByExCode(exam.getTestCode() + "A");
            if (examDTO != null && examDTO.getExQuestionIDs() != null && !examDTO.getExQuestionIDs().isEmpty()) {
                questionIDs = Arrays.asList(examDTO.getExQuestionIDs().split(","));
            } else {
                questionIDs.add("Chưa có câu hỏi nào");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu câu hỏi: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            questionIDs.add("Lỗi tải dữ liệu");
        }
    }

    private void initializeUI() {
        // Header Panel
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(66, 139, 202), 0, getHeight(),
                        new Color(52, 110, 158));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 50));
        headerPanel.setLayout(new BorderLayout(10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitle = new JLabel("Danh Sách Câu Hỏi - " + exam.getTitle());
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        // Toolbar Panel
        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        toolbarPanel.setOpaque(false);
        toolbarPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ImageIcon importIcon = new ImageIcon(
                getClass().getResource("/org/example/GUI/resources/images/icons8_import_30px.png"));
        ImageIcon exportIcon = new ImageIcon(
                getClass().getResource("/org/example/GUI/resources/images/icons8_ms_excel_30px.png"));

        btnImport = createStyledButton("Import", new Color(52, 152, 219), importIcon);
        btnExport = createStyledButton("Export", new Color(39, 174, 96), exportIcon);
        btnSearch = createStyledButton("Tìm Kiếm", new Color(241, 196, 15), null);

        searchField = new JTextField(15);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(150, 30));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        btnImport.addActionListener(e -> importQuestions());
        btnExport.addActionListener(e -> exportToExcel());
        btnSearch.addActionListener(e -> searchQuestions());

        toolbarPanel.add(btnImport);
        toolbarPanel.add(btnExport);
        toolbarPanel.add(new JLabel("Tìm kiếm:"));
        toolbarPanel.add(searchField);
        toolbarPanel.add(btnSearch);

        // Table Panel
        tableModel = new DefaultTableModel(new Object[] { "STT", "ID Câu Hỏi" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        questionTable = new JTable(tableModel);
        questionTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        questionTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        questionTable.setRowHeight(25);
        questionTable.setGridColor(new Color(220, 220, 220));
        questionTable.setShowGrid(true);
        questionTable.setSelectionBackground(new Color(52, 152, 219));
        questionTable.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        questionTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        questionTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        // Thêm sự kiện nhấp đúp để xem chi tiết câu hỏi
        questionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) { // Nhấp đúp
                    int selectedRow = questionTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        String qidStr = (String) tableModel.getValueAt(selectedRow, 1);
                        try {
                            int qid = Integer.parseInt(qidStr);
                            showQuestionDetails(qid);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(DialogViewDetailTest.this, "ID câu hỏi không hợp lệ!", "Lỗi",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        refreshTable(questionIDs);

        JScrollPane tableScrollPane = new JScrollPane(questionTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1), "Danh Sách Câu Hỏi"));

        // Add components to dialog
        add(toolbarPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JButton btnClose = createStyledButton("Đóng", new Color(231, 76, 60), null);
        btnClose.addActionListener(e -> dispose());
        buttonPanel.add(btnClose);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text, Color bgColor, ImageIcon icon) {
        JButton button = new JButton(text, icon) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        return button;
    }

    private void refreshTable(List<String> questions) {
        tableModel.setRowCount(0);
        for (int i = 0; i < questions.size(); i++) {
            tableModel.addRow(new Object[] { i + 1, questions.get(i) });
        }
    }

    private void importQuestions() {
        String newQuestions = JOptionPane.showInputDialog(this, "Nhập danh sách ID câu hỏi (cách nhau bởi dấu phẩy):");
        if (newQuestions != null && !newQuestions.trim().isEmpty()) {
            questionIDs = Arrays.asList(newQuestions.split(","));
            refreshTable(questionIDs);
            updateExamQuestions();
            JOptionPane.showMessageDialog(this, "Đã import danh sách câu hỏi!", "Thành Công",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void exportToExcel() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Question List");
            Row headerRow = sheet.createRow(0);
            String[] headers = { "STT", "ID Câu Hỏi" };
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            for (int i = 0; i < questionIDs.size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(questionIDs.get(i));
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(exam.getTitle() + "_Questions.xlsx"));
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    workbook.write(fos);
                    JOptionPane.showMessageDialog(this, "Đã xuất danh sách câu hỏi ra Excel!", "Thành Công",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất Excel: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchQuestions() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            refreshTable(questionIDs);
        } else {
            List<String> filteredQuestions = new ArrayList<>();
            for (String qid : questionIDs) {
                if (qid.toLowerCase().contains(query)) {
                    filteredQuestions.add(qid);
                }
            }
            refreshTable(filteredQuestions);
        }
    }

    private void updateExamQuestions() {
        String exCode = exam.getTestCode() + "A";
        try {
            ExamsDTO examDTO = examBUS.getExamByExCode(exCode);
            if (examDTO != null) {
                examDTO.setExQuestionIDs(String.join(",", questionIDs));
                examBUS.updateExam(examDTO);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật câu hỏi: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void showQuestionDetails(int qid) {
        JDialog detailDialog = new JDialog(this, "Chi Tiết Câu Hỏi: " + qid, true);
        detailDialog.setLayout(new BorderLayout(10, 10));
        detailDialog.setSize(500, 400);
        detailDialog.setLocationRelativeTo(this);

        try {
            QuestionDTO question = questionBUS.getQuestionByID(qid);
            if (question != null) {
                JLabel contentLabel = new JLabel("<html><b>Câu hỏi:</b> " + question.getQContent() + "</html>");
                contentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                contentLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JPanel answersPanel = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));
                answersPanel.setBorder(BorderFactory.createTitledBorder("Đáp án"));
                List<AnswersDTO> answers = answersBUS.getAnswersByQuestionID(qid);
                for (AnswersDTO answer : answers) {
                    JPanel answerPanel = new JPanel(new BorderLayout());
                    JLabel answerLabel = new JLabel(answer.getAwContent() + (answer.getIsRight() ? " (Đúng)" : ""));
                    answerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                    answerPanel.add(answerLabel, BorderLayout.CENTER);
                    // Chỉ hiển thị hình ảnh nếu awPicture tồn tại và không rỗng
                    if (answer.getAwPicture() != null && !answer.getAwPicture().isEmpty()) {
                        try {
                            ImageIcon image = new ImageIcon(answer.getAwPicture());
                            JLabel imageLabel = new JLabel(image);
                            answerPanel.add(imageLabel, BorderLayout.EAST);
                        } catch (Exception e) {
                            answerPanel.add(new JLabel("(Hình ảnh: " + answer.getAwPicture() + ")"), BorderLayout.EAST);
                        }
                    }
                    answersPanel.add(answerPanel);
                }

                JButton btnClose = createStyledButton("Đóng", new Color(231, 76, 60), null);
                btnClose.addActionListener(e -> detailDialog.dispose());

                detailDialog.add(contentLabel, BorderLayout.NORTH);
                detailDialog.add(new JScrollPane(answersPanel), BorderLayout.CENTER);
                detailDialog.add(btnClose, BorderLayout.SOUTH);
                detailDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy câu hỏi với ID: " + qid, "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải chi tiết câu hỏi: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> {
    // TestManagementPanel panel = new TestManagementPanel();
    // List<TestManagementPanel.Exam> examList = new ArrayList<>();
    // examList.add(new TestManagementPanel.Exam(1, "Kỳ Thi Toán", "Toán Học",
    // "2025-03-15", "MATH01"));
    // DialogViewDetailTest dialog = new DialogViewDetailTest(panel,
    // examList.get(0), examList);
    // dialog.setVisible(true);
    // });
    // }
}