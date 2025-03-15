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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
    private JButton btnImport, btnExport, btnSearch, btnClose;
    private List<QuestionDetail> questionDetails; // Danh sách câu hỏi và đáp án
    private ExamBUS examBUS;
    private QuestionBUS questionBUS;
    private AnswersBUS answersBUS;

    // Lớp nội bộ để lưu thông tin câu hỏi và đáp án
    private static class QuestionDetail {
        private int qid;
        private String content;
        private String answers; // Chuỗi chứa tất cả đáp án và trạng thái đúng/sai

        public QuestionDetail(int qid, String content, String answers) {
            this.qid = qid;
            this.content = content;
            this.answers = answers;
        }

        public int getQid() {
            return qid;
        }

        public String getContent() {
            return content;
        }

        public String getAnswers() {
            return answers;
        }
    }

    public DialogViewDetailTest(TestManagementPanel parentPanel, TestManagementPanel.Exam exam,
            List<TestManagementPanel.Exam> examList) {
        super((Frame) SwingUtilities.getWindowAncestor(parentPanel), "Chi Tiết Kỳ Thi: " + exam.getTitle(), true);
        this.parentPanel = parentPanel;
        this.exam = exam;
        this.examList = examList;
        this.examBUS = new ExamBUS();
        this.questionBUS = new QuestionBUS();
        this.answersBUS = new AnswersBUS();
        this.questionDetails = new ArrayList<>();
        setLayout(new BorderLayout(10, 10));
        setSize(800, 600); // Tăng kích thước để hiển thị nội dung đầy đủ
        setLocationRelativeTo(parentPanel);
        loadQuestionData();
        initializeUI();
    }

    private void loadQuestionData() {
        try {
            ExamsDTO examDTO = examBUS.getExamByExCode(exam.getTestCode() + "A");
            if (examDTO != null && examDTO.getExQuestionIDs() != null && !examDTO.getExQuestionIDs().isEmpty()) {
                List<String> questionIDs = Arrays.asList(examDTO.getExQuestionIDs().split(","));
                for (String qidStr : questionIDs) {
                    try {
                        int qid = Integer.parseInt(qidStr.trim());
                        QuestionDTO question = questionBUS.getQuestionByID(qid);
                        if (question != null) {
                            List<AnswersDTO> answers = answersBUS.getAnswersByQuestionID(qid);
                            StringBuilder answerText = new StringBuilder();
                            for (AnswersDTO answer : answers) {
                                answerText.append(answer.getAwContent())
                                        .append(answer.getIsRight() ? " (Đúng)" : " (Sai)")
                                        .append("; ");
                            }
                            if (answerText.length() > 2) {
                                answerText.setLength(answerText.length() - 2); // Xóa dấu "; " cuối
                            }
                            questionDetails.add(new QuestionDetail(qid, question.getQContent(), answerText.toString()));
                        }
                    } catch (NumberFormatException e) {
                        questionDetails.add(new QuestionDetail(-1, "ID không hợp lệ: " + qidStr, "N/A"));
                    }
                }
            } else {
                questionDetails.add(new QuestionDetail(-1, "Chưa có câu hỏi nào", "N/A"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu câu hỏi: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            questionDetails.add(new QuestionDetail(-1, "Lỗi tải dữ liệu", "N/A"));
        }
    }

    private void initializeUI() {
        // Header Panel
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(66, 139, 202), 0, getHeight(),
                        new Color(52, 110, 158));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 50));
        headerPanel.setLayout(new BorderLayout());
        JLabel lblTitle = new JLabel("Danh Sách Câu Hỏi - " + exam.getTitle());
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(new EmptyBorder(0, 15, 0, 0));
        headerPanel.add(lblTitle, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        // Toolbar Panel
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.X_AXIS));
        toolbarPanel.setOpaque(false);
        toolbarPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        btnImport = createStyledButton("Import", new Color(52, 152, 219),
                "/org/example/GUI/resources/images/icons8_import_30px.png");
        btnExport = createStyledButton("Export", new Color(39, 174, 96),
                "/org/example/GUI/resources/images/icons8_ms_excel_30px.png");
        btnSearch = createStyledButton("Tìm Kiếm", new Color(241, 196, 15), null);

        searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(200, 35));
        searchField.setMaximumSize(new Dimension(200, 35));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        btnImport.addActionListener(e -> importQuestions());
        btnExport.addActionListener(e -> exportToExcel());
        btnSearch.addActionListener(e -> searchQuestions());

        toolbarPanel.add(btnImport);
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(btnExport);
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(new JLabel("Tìm kiếm:"));
        toolbarPanel.add(Box.createHorizontalStrut(5));
        toolbarPanel.add(searchField);
        toolbarPanel.add(Box.createHorizontalStrut(5));
        toolbarPanel.add(btnSearch);
        toolbarPanel.add(Box.createHorizontalGlue());

        add(toolbarPanel, BorderLayout.CENTER);

        // Table Panel
        tableModel = new DefaultTableModel(new Object[] { "STT", "ID Câu Hỏi", "Nội Dung Câu Hỏi", "Đáp Án" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        questionTable = new JTable(tableModel);
        questionTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        questionTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        questionTable.setRowHeight(30);
        questionTable.setGridColor(new Color(220, 220, 220));
        questionTable.setShowGrid(true);
        questionTable.setSelectionBackground(new Color(52, 152, 219));
        questionTable.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        questionTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // STT
        questionTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // ID
        questionTable.getColumnModel().getColumn(2).setPreferredWidth(300); // Nội dung rộng hơn
        questionTable.getColumnModel().getColumn(3).setPreferredWidth(400); // Đáp án rộng hơn

        refreshTable(questionDetails);

        JScrollPane tableScrollPane = new JScrollPane(questionTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1), "Danh Sách Câu Hỏi và Đáp Án"));
        add(tableScrollPane, BorderLayout.SOUTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        btnClose = createStyledButton("Đóng", new Color(231, 76, 60), null);
        btnClose.addActionListener(e -> dispose());
        buttonPanel.add(btnClose);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text, Color bgColor, String iconPath) {
        JButton button = new JButton(text, iconPath != null ? new ImageIcon(getClass().getResource(iconPath)) : null) {
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
        button.setBorder(new EmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }

            public void mousePressed(MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseReleased(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        return button;
    }

    private void refreshTable(List<QuestionDetail> questions) {
        tableModel.setRowCount(0);
        for (int i = 0; i < questions.size(); i++) {
            QuestionDetail q = questions.get(i);
            tableModel.addRow(new Object[] { i + 1, q.getQid(), q.getContent(), q.getAnswers() });
        }
    }

    private void importQuestions() {
        String newQuestions = JOptionPane.showInputDialog(this, "Nhập danh sách ID câu hỏi (cách nhau bởi dấu phẩy):");
        if (newQuestions != null && !newQuestions.trim().isEmpty()) {
            questionDetails.clear();
            List<String> questionIDs = Arrays.asList(newQuestions.split(","));
            for (String qidStr : questionIDs) {
                try {
                    int qid = Integer.parseInt(qidStr.trim());
                    QuestionDTO question = questionBUS.getQuestionByID(qid);
                    if (question != null) {
                        List<AnswersDTO> answers = answersBUS.getAnswersByQuestionID(qid);
                        StringBuilder answerText = new StringBuilder();
                        for (AnswersDTO answer : answers) {
                            answerText.append(answer.getAwContent())
                                    .append(answer.getIsRight() ? " (Đúng)" : " (Sai)")
                                    .append("; ");
                        }
                        if (answerText.length() > 2) {
                            answerText.setLength(answerText.length() - 2);
                        }
                        questionDetails.add(new QuestionDetail(qid, question.getQContent(), answerText.toString()));
                    }
                } catch (NumberFormatException e) {
                    questionDetails.add(new QuestionDetail(-1, "ID không hợp lệ: " + qidStr, "N/A"));
                }
            }
            refreshTable(questionDetails);
            updateExamQuestions();
            JOptionPane.showMessageDialog(this, "Đã import danh sách câu hỏi!", "Thành Công",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void exportToExcel() {
        try (org.apache.poi.xssf.usermodel.XSSFWorkbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Question List");
            Row headerRow = sheet.createRow(0);
            String[] headers = { "STT", "ID Câu Hỏi", "Nội Dung Câu Hỏi", "Đáp Án" };
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            for (int i = 0; i < questionDetails.size(); i++) {
                Row row = sheet.createRow(i + 1);
                QuestionDetail q = questionDetails.get(i);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(q.getQid());
                row.createCell(2).setCellValue(q.getContent());
                row.createCell(3).setCellValue(q.getAnswers());
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
            refreshTable(questionDetails);
        } else {
            List<QuestionDetail> filteredQuestions = new ArrayList<>();
            for (QuestionDetail q : questionDetails) {
                if (String.valueOf(q.getQid()).contains(query) || q.getContent().toLowerCase().contains(query) ||
                        q.getAnswers().toLowerCase().contains(query)) {
                    filteredQuestions.add(q);
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
                String questionIDs = questionDetails.stream()
                        .filter(q -> q.getQid() != -1)
                        .map(q -> String.valueOf(q.getQid()))
                        .collect(java.util.stream.Collectors.joining(","));
                examDTO.setExQuestionIDs(questionIDs);
                examBUS.updateExam(examDTO);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật câu hỏi: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}