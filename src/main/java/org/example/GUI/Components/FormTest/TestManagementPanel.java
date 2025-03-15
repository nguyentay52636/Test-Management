package org.example.GUI.Components.FormTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.BUS.ExamBUS;
import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.ExamsDTO;
import org.example.GUI.FormDialog.DialogTest.DialogAddTest;
import org.example.GUI.FormDialog.DialogTest.DialogViewDetailTest;

public class TestManagementPanel extends JPanel {
    private JTable examTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton btnAddExam, btnAddTopic, btnViewDetails, btnDeleteExam, btnExportExcel, btnSearch;
    private List<Exam> examList;
    private ExamBUS examBUS;

    // Lớp Exam để hiển thị dữ liệu trong bảng
    public static class Exam {
        private int id; // testID từ bảng test
        private String title; // testTitle từ bảng test
        private String topic; // tpTitle từ bảng topics
        private String date; // testDate từ bảng test
        private String testCode; // testCode từ bảng test
        private int topicStatus; // tpStatus từ bảng topics

        public Exam(int id, String title, String topic, String date, String testCode, int topicStatus) {
            this.id = id;
            this.title = title;
            this.topic = topic;
            this.date = date;
            this.testCode = testCode;
            this.topicStatus = topicStatus;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getTopic() {
            return topic;
        }

        public String getDate() {
            return date;
        }

        public String getTestCode() {
            return testCode;
        }

        public int getTopicStatus() {
            return topicStatus;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public TestManagementPanel() {
        examList = new ArrayList<>();
        examBUS = new ExamBUS();
        setBackground(new Color(240, 242, 245));
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        initializeUI();
        loadExamDataFromDB();
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
        headerPanel.setPreferredSize(new Dimension(0, 60));
        headerPanel.setLayout(new BorderLayout(10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitle = new JLabel("Quản Lý Kỳ Thi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setOpaque(false);

        // Toolbar Panel
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.X_AXIS));
        toolbarPanel.setOpaque(false);
        toolbarPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Icons
        ImageIcon iconThem = new ImageIcon(
                getClass().getResource("/org/example/GUI/resources/images/icons8_add_30px.png"));
        ImageIcon exportIcon = new ImageIcon(
                getClass().getResource("/org/example/GUI/resources/images/icons8_ms_excel_30px.png"));

        // Buttons
        btnAddExam = createStyledButton("Thêm Kỳ Thi", new Color(52, 152, 219), iconThem);
        btnAddTopic = createStyledButton("Thêm Chủ Đề", new Color(46, 204, 113), null);
        btnViewDetails = createStyledButton("Xem Chi Tiết", new Color(241, 196, 15), null);
        btnDeleteExam = createStyledButton("Xóa Kỳ Thi", new Color(231, 76, 60), null);
        btnExportExcel = createStyledButton("Xuất Excel", new Color(39, 174, 96), exportIcon);
        btnSearch = createStyledButton("Tìm Kiếm", new Color(52, 152, 219), null);

        // Search Field
        searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(200, 35));
        searchField.setMaximumSize(new Dimension(200, 35));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Button Actions
        btnAddExam.addActionListener(e -> addExam());
        btnAddTopic.addActionListener(e -> addTopic());
        btnViewDetails.addActionListener(e -> viewExamDetails());
        btnDeleteExam.addActionListener(e -> deleteExam());
        btnExportExcel.addActionListener(e -> exportToExcel());
        btnSearch.addActionListener(e -> searchExams());

        // Thêm các thành phần vào toolbarPanel với khoảng cách
        toolbarPanel.add(btnAddExam);
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(btnAddTopic);
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(btnViewDetails);
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(btnDeleteExam);
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(btnExportExcel);
        toolbarPanel.add(Box.createHorizontalStrut(10));
        toolbarPanel.add(new JLabel("Tìm kiếm:"));
        toolbarPanel.add(Box.createHorizontalStrut(5));
        toolbarPanel.add(searchField);
        toolbarPanel.add(Box.createHorizontalStrut(5));
        toolbarPanel.add(btnSearch);
        toolbarPanel.add(Box.createHorizontalGlue());

        contentPanel.add(toolbarPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(
                new Object[] { "ID", "Tên Kỳ Thi", "Chủ Đề", "Ngày Thi", "Trạng Thái Chủ Đề" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        examTable = new JTable(tableModel);
        examTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        examTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        examTable.setRowHeight(30);
        examTable.setGridColor(new Color(220, 220, 220));
        examTable.setShowGrid(true);
        examTable.setSelectionBackground(new Color(52, 152, 219));
        examTable.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        examTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        examTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        examTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Căn giữa cột Trạng Thái

        JScrollPane tableScrollPane = new JScrollPane(examTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1), "Danh Sách Kỳ Thi"));
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
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

    private void loadExamDataFromDB() {
        examList.clear();
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT t.testID, t.testTitle, t.testDate, tp.tpTitle, t.testCode, tp.tpStatus " +
                                "FROM test t " +
                                "LEFT JOIN test_structure ts ON t.testCode = ts.testCode " +
                                "LEFT JOIN topics tp ON ts.tpID = tp.tpID")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("testID");
                String title = rs.getString("testTitle");
                String topic = rs.getString("tpTitle") != null ? rs.getString("tpTitle") : "Chưa xác định";
                String date = rs.getString("testDate");
                String testCode = rs.getString("testCode");
                int topicStatus = rs.getInt("tpStatus"); // Lấy tpStatus từ bảng topics
                examList.add(new Exam(id, title, topic, date, testCode, topicStatus));
            }
            refreshTable(examList);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshTable(List<Exam> exams) {
        tableModel.setRowCount(0);
        for (Exam exam : exams) {
            tableModel.addRow(new Object[] {
                    exam.getId(),
                    exam.getTitle(),
                    exam.getTopic(),
                    exam.getDate(),
                    exam.getTopicStatus() == 1 ? "Hoạt động" : "Không hoạt động" // Hiển thị trạng thái dưới dạng văn
                                                                                 // bản
            });
        }
    }

    private void addExam() {
        DialogAddTest dialog = new DialogAddTest(this, null, examList);
        dialog.setVisible(true);
        Exam newExam = dialog.getNewExam();
        if (newExam != null) {
            try (Connection conn = UtilsJDBC.getConnectDB()) {
                String testCode = "TEST" + String.format("%03d", newExam.getId());
                PreparedStatement psTest = conn.prepareStatement(
                        "INSERT INTO test (testCode, testTitle, testTime, testLimit, testDate, testStatus) " +
                                "VALUES (?, ?, ?, ?, ?, ?)");
                psTest.setString(1, testCode);
                psTest.setString(2, newExam.getTitle());
                psTest.setInt(3, 10);
                psTest.setInt(4, 1);
                psTest.setString(5, newExam.getDate());
                psTest.setInt(6, 1);
                psTest.executeUpdate();

                PreparedStatement psStructure = conn.prepareStatement(
                        "INSERT INTO test_structure (testCode, tpID, numberEasy, numberMedium, numberDiff) " +
                                "VALUES (?, (SELECT tpID FROM topics WHERE tpTitle = ?), ?, ?, ?)");
                psStructure.setString(1, testCode);
                psStructure.setString(2, newExam.getTopic());
                psStructure.setInt(3, 5);
                psStructure.setInt(4, 3);
                psStructure.setInt(5, 2);
                psStructure.executeUpdate();

                examList.add(newExam);
                refreshTable(examList);
                JOptionPane.showMessageDialog(this, "Đã thêm kỳ thi mới!", "Thành Công",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi thêm kỳ thi: " + e.getMessage(), "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addTopic() {
        String topic = JOptionPane.showInputDialog(this, "Nhập tên chủ đề mới:");
        if (topic != null && !topic.trim().isEmpty()) {
            try (Connection conn = UtilsJDBC.getConnectDB();
                    PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO topics (tpTitle, tpParent, tpStatus) VALUES (?, 0, 1)")) {
                ps.setString(1, topic);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Đã thêm chủ đề '" + topic + "'", "Thành Công",
                        JOptionPane.INFORMATION_MESSAGE);
                loadExamDataFromDB(); // Tải lại dữ liệu để cập nhật chủ đề mới
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi thêm chủ đề: " + e.getMessage(), "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void viewExamDetails() {
        int selectedRow = examTable.getSelectedRow();
        if (selectedRow >= 0) {
            Exam selectedExam = examList.get(selectedRow);
            try {
                ExamsDTO examDTO = examBUS.getExamByExCode(selectedExam.getTestCode() + "A");
                if (examDTO != null) {
                    new DialogViewDetailTest(this, selectedExam, examList).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Chưa có chi tiết cho kỳ thi này!", "Thông Báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage(), "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một kỳ thi để xem chi tiết!", "Lỗi",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteExam() {
        int selectedRow = examTable.getSelectedRow();
        if (selectedRow >= 0) {
            Exam selectedExam = examList.get(selectedRow);
            int confirm = JOptionPane.showConfirmDialog(this, "Xóa kỳ thi '" + selectedExam.getTitle() + "'?",
                    "Xác Nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String exCode = selectedExam.getTestCode() + "A";
                if (examBUS.deleteExam(exCode)) {
                    try (Connection conn = UtilsJDBC.getConnectDB();
                            PreparedStatement ps = conn.prepareStatement("DELETE FROM test WHERE testID = ?")) {
                        ps.setInt(1, selectedExam.getId());
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Lỗi xóa từ bảng test: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    examList.remove(selectedRow);
                    refreshTable(examList);
                    JOptionPane.showMessageDialog(this, "Đã xóa kỳ thi!", "Thành Công",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi xóa kỳ thi từ exams!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kỳ thi để xóa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void exportToExcel() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Exam List");
            Row headerRow = sheet.createRow(0);
            String[] headers = { "ID", "Tên Kỳ Thi", "Chủ Đề", "Ngày Thi", "Trạng Thái Chủ Đề" };
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            for (int i = 0; i < examList.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Exam exam = examList.get(i);
                row.createCell(0).setCellValue(exam.getId());
                row.createCell(1).setCellValue(exam.getTitle());
                row.createCell(2).setCellValue(exam.getTopic());
                row.createCell(3).setCellValue(exam.getDate());
                row.createCell(4).setCellValue(exam.getTopicStatus() == 1 ? "Hoạt động" : "Không hoạt động");
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("ExamList.xlsx"));
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    workbook.write(fos);
                    JOptionPane.showMessageDialog(this, "Đã xuất danh sách kỳ thi ra Excel!", "Thành Công",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất Excel: " + e.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchExams() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            refreshTable(examList);
        } else {
            List<Exam> filteredList = new ArrayList<>();
            for (Exam exam : examList) {
                if (exam.getTitle().toLowerCase().contains(query) || exam.getTopic().toLowerCase().contains(query)) {
                    filteredList.add(exam);
                }
            }
            refreshTable(filteredList);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Kỳ Thi");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 700); // Tăng kích thước để hiển thị cột mới
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new TestManagementPanel());
            frame.setVisible(true);
        });
    }
}