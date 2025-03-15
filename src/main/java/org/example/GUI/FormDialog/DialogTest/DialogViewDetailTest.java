package org.example.GUI.FormDialog.DialogTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
    private JButton btnImport, btnExport, btnSearch, btnClose;
    private List<QuestionDetail> questionDetails;
    private ExamBUS examBUS;
    private QuestionBUS questionBUS;
    private AnswersBUS answersBUS;

    // Lớp nội bộ để lưu thông tin câu hỏi và đáp án đúng
    private static class QuestionDetail {
        private int qid;
        private String content;
        private String correctAnswers;

        public QuestionDetail(int qid, String content, String correctAnswers) {
            this.qid = qid;
            this.content = content;
            this.correctAnswers = correctAnswers;
        }

        public int getQid() {
            return qid;
        }

        public String getContent() {
            return content;
        }

        public String getCorrectAnswers() {
            return correctAnswers;
        }
    }

    public DialogViewDetailTest(TestManagementPanel parentPanel, TestManagementPanel.Exam exam,
            List<TestManagementPanel.Exam> examList) {
        super((Frame) SwingUtilities.getWindowAncestor(parentPanel), "Chi Tiết Kỳ Thi: " + exam.getTitle(), true);
        System.out.println("Đang hiển thị chi tiết kỳ thi: " + exam.getTitle());
        this.parentPanel = parentPanel;
        this.exam = exam;
        this.examList = examList;
        this.questionDetails = new ArrayList<>();
        this.examBUS = new ExamBUS();
        this.questionBUS = new QuestionBUS();
        this.answersBUS = new AnswersBUS();
        setLayout(new BorderLayout(10, 10));
        setSize(1000, 600);
        setLocationRelativeTo(parentPanel);
        loadQuestionData(); // Tải dữ liệu vào bảng
        initializeUI();
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
        JLabel lblTitle = new JLabel("Danh Sách Câu Hỏi - " + exam.getTitle() + " (" + exam.getExCode() + ")");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(new EmptyBorder(0, 15, 0, 0));
        headerPanel.add(lblTitle, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        // Toolbar Panel (Thanh ngang chứa các nút và ô tìm kiếm)
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.X_AXIS));
        toolbarPanel.setOpaque(false);
        toolbarPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        toolbarPanel.setBackground(new Color(245, 245, 245)); // Màu nền nhẹ cho thanh công cụ

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

        btnClose = createStyledButton("Đóng", new Color(231, 76, 60), null);
        btnClose.addActionListener(e -> dispose());

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
        toolbarPanel.add(Box.createHorizontalGlue()); // Đẩy nút "Đóng" sang phải
        toolbarPanel.add(btnClose);

        add(toolbarPanel, BorderLayout.CENTER);

        // Table Panel (Bảng hiển thị dữ liệu)
        tableModel = new DefaultTableModel(new Object[] { "STT", "ID Câu Hỏi", "Nội Dung Câu Hỏi", "Đáp Án Đúng" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        questionTable = new JTable(tableModel);
        questionTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        questionTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        questionTable.getTableHeader().setBackground(new Color(66, 139, 202));
        questionTable.getTableHeader().setForeground(Color.WHITE);
        questionTable.setRowHeight(30);
        questionTable.setGridColor(new Color(220, 220, 220));
        questionTable.setShowGrid(true);
        questionTable.setSelectionBackground(new Color(52, 152, 219));
        questionTable.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        questionTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // STT
        questionTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // ID
        questionTable.getColumnModel().getColumn(2).setPreferredWidth(400); // Nội dung rộng hơn
        questionTable.getColumnModel().getColumn(3).setPreferredWidth(300); // Đáp án đúng rộng hơn

        refreshTable(questionDetails); // Hiển thị dữ liệu vào bảng

        JScrollPane tableScrollPane = new JScrollPane(questionTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1), "Danh Sách Câu Hỏi và Đáp Án Đúng"));
        add(tableScrollPane, BorderLayout.SOUTH); // Bảng chiếm phần còn lại của giao diện
    }

    private JButton createStyledButton(String text, Color bgColor, String iconPath) {
        ImageIcon icon = null;
        if (iconPath != null) {
            java.net.URL imgURL = getClass().getResource(iconPath);
            if (imgURL != null) {
                icon = new ImageIcon(imgURL);
            } else {
                System.err.println("Không tìm thấy tài nguyên: " + iconPath);
            }
        }
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
        button.setBorder(new EmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        return button;
    }

    private String getCorrectAnswers(List<AnswersDTO> answers) {
        StringBuilder correctAnswerText = new StringBuilder();
        for (AnswersDTO answer : answers) {
            if (answer.getIsRight()) {
                correctAnswerText.append(answer.getAwContent()).append("; ");
            }
        }
        if (correctAnswerText.length() > 2) {
            correctAnswerText.setLength(correctAnswerText.length() - 2); // Xóa "; " cuối
        }
        return correctAnswerText.length() > 0 ? correctAnswerText.toString() : "N/A";
    }

    private void refreshTable(List<QuestionDetail> questions) {
        tableModel.setRowCount(0);
        for (int i = 0; i < questions.size(); i++) {
            QuestionDetail q = questions.get(i);
            tableModel.addRow(new Object[] { i + 1, q.getQid(), q.getContent(), q.getCorrectAnswers() });
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
                        String correctAnswer = getCorrectAnswers(answers);
                        questionDetails.add(new QuestionDetail(qid, question.getQContent(), correctAnswer));
                    } else {
                        questionDetails.add(new QuestionDetail(qid, "Không tìm thấy câu hỏi", "N/A"));
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
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Question List");
            Row headerRow = sheet.createRow(0);
            String[] headers = { "STT", "ID Câu Hỏi", "Nội Dung Câu Hỏi", "Đáp Án Đúng" };
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            for (int i = 0; i < questionDetails.size(); i++) {
                Row row = sheet.createRow(i + 1);
                QuestionDetail q = questionDetails.get(i);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(q.getQid());
                row.createCell(2).setCellValue(q.getContent());
                row.createCell(3).setCellValue(q.getCorrectAnswers());
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
            e.printStackTrace();
        }
    }

    private void searchQuestions() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            refreshTable(questionDetails);
        } else {
            List<QuestionDetail> filteredQuestions = new ArrayList<>();
            for (QuestionDetail q : questionDetails) {
                if (String.valueOf(q.getQid()).contains(query) ||
                        q.getContent().toLowerCase().contains(query) ||
                        q.getCorrectAnswers().toLowerCase().contains(query)) {
                    filteredQuestions.add(q);
                }
            }
            refreshTable(filteredQuestions);
        }
    }

    private void updateExamQuestions() {
        try {
            ExamsDTO examDTO = examBUS.getExamByExCode(exam.getExCode());
            if (examDTO != null) {
                String questionIDs = questionDetails.stream()
                        .filter(q -> q.getQid() != -1)
                        .map(q -> String.valueOf(q.getQid()))
                        .collect(java.util.stream.Collectors.joining(","));
                examDTO.setExQuestionIDs(questionIDs);
                if (examBUS.updateExam(examDTO)) {
                    JOptionPane.showMessageDialog(this, "Đã cập nhật danh sách câu hỏi!", "Thành Công",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể cập nhật câu hỏi!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin đề thi!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật câu hỏi: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void loadQuestionData() {
        try {
            System.out.println("Đang tải dữ liệu câu hỏi cho kỳ thi: " + exam.getExCode());
            ExamsDTO examDTO = examBUS.getExamByExCode(exam.getExCode());

            if (examDTO == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kỳ thi với mã: " + exam.getExCode(), "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                questionDetails.add(new QuestionDetail(-1, "Không tìm thấy kỳ thi", "N/A"));
                return;
            }
            if (examDTO.getExQuestionIDs() == null || examDTO.getExQuestionIDs().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chưa có câu hỏi nào cho kỳ thi này!", "Thông Báo",
                        JOptionPane.INFORMATION_MESSAGE);
                questionDetails.add(new QuestionDetail(-1, "Chưa có câu hỏi nào", "N/A"));
                return;
            }

            List<String> questionIDs = Arrays.asList(examDTO.getExQuestionIDs().split(","));
            for (String qidStr : questionIDs) {
                try {
                    int qid = Integer.parseInt(qidStr.trim());
                    QuestionDTO question = questionBUS.getQuestionByID(qid);
                    if (question != null) {
                        List<AnswersDTO> answers = answersBUS.getAnswersByQuestionID(qid);
                        String correctAnswer = getCorrectAnswers(answers);
                        questionDetails.add(new QuestionDetail(qid, question.getQContent(), correctAnswer));
                    } else {
                        questionDetails.add(new QuestionDetail(qid, "Không tìm thấy câu hỏi", "N/A"));
                    }
                } catch (NumberFormatException e) {
                    questionDetails.add(new QuestionDetail(-1, "ID không hợp lệ: " + qidStr, "N/A"));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu câu hỏi: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            questionDetails.add(new QuestionDetail(-1, "Lỗi tải dữ liệu", "N/A"));
        }
    }

}