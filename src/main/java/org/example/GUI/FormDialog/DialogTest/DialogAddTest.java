package org.example.GUI.FormDialog.DialogTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.example.GUI.Components.FormTest.TestManagementPanel;

public class DialogAddTest extends JDialog {
    private JTextField titleField, topicField, dateField;
    private TestManagementPanel parentPanel;
    private TestManagementPanel.Exam examToEdit;
    private List<TestManagementPanel.Exam> examList;
    private TestManagementPanel.Exam newExam; // Để lưu trữ Exam mới hoặc đã chỉnh sửa

    public DialogAddTest(TestManagementPanel parentPanel, TestManagementPanel.Exam examToEdit,
            List<TestManagementPanel.Exam> examList) {
        super((Frame) SwingUtilities.getWindowAncestor(parentPanel),
                examToEdit == null ? "Thêm Kỳ Thi" : "Chỉnh Sửa Kỳ Thi", true);
        this.parentPanel = parentPanel;
        this.examToEdit = examToEdit;
        this.examList = examList;
        this.newExam = null; // Khởi tạo null, sẽ được gán giá trị trong saveExam()
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setSize(400, 250);
        setLocationRelativeTo(parentPanel);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(245, 247, 250));

        JLabel lblTitle = new JLabel("Tên kỳ thi:");
        titleField = new JTextField(20);
        JLabel lblTopic = new JLabel("Chủ đề:");
        topicField = new JTextField(20);
        JLabel lblDate = new JLabel("Ngày thi (YYYY-MM-DD):");
        dateField = new JTextField(20);

        // Nếu là chỉnh sửa, điền dữ liệu hiện tại
        if (examToEdit != null) {
            titleField.setText(examToEdit.getTitle());
            topicField.setText(examToEdit.getTopic());
            dateField.setText(examToEdit.getDate());
        }

        inputPanel.add(lblTitle);
        inputPanel.add(titleField);
        inputPanel.add(lblTopic);
        inputPanel.add(topicField);
        inputPanel.add(lblDate);
        inputPanel.add(dateField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(245, 247, 250));
        JButton btnSave = createStyledButton("Lưu", new Color(46, 204, 113));
        JButton btnCancel = createStyledButton("Hủy", new Color(231, 76, 60));

        btnSave.addActionListener(e -> saveExam());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
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

    private void saveExam() {
        String title = titleField.getText().trim();
        String topic = topicField.getText().trim();
        String date = dateField.getText().trim();

        // Kiểm tra dữ liệu đầu vào
        if (title.isEmpty() || topic.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra định dạng ngày (YYYY-MM-DD) đơn giản
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Ngày thi phải có định dạng YYYY-MM-DD!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (examToEdit == null) {
            // Thêm mới kỳ thi
            // int newId = getNextId(); // Lấy ID mới từ danh sách hoặc DB
            // String testCode = "TEST" + String.format("%03d", newId); // Tạo testCode, ví
            // dụ: TEST001
            // newExam = new TestManagementPanel.Exam(newId, title, topic, date, testCode);
            // examList.add(newExam);
            JOptionPane.showMessageDialog(this, "Đã thêm kỳ thi mới!", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Chỉnh sửa kỳ thi
            examToEdit.setTitle(title);
            examToEdit.setTopic(topic);
            examToEdit.setDate(date);
            newExam = examToEdit; // Gán examToEdit vào newExam để trả về
            JOptionPane.showMessageDialog(this, "Đã cập nhật kỳ thi!", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
        }

        parentPanel.refreshTable(examList); // Cập nhật bảng
        dispose(); // Đóng dialog
    }

    // Phương thức lấy ID mới (dựa trên danh sách hiện tại, có thể thay bằng truy
    // vấn DB)
    private int getNextId() {
        int maxId = 0;
        for (TestManagementPanel.Exam exam : examList) {
            if (exam.getId() > maxId) {
                maxId = exam.getId();
            }
        }
        return maxId + 1;
    }

    // Phương thức trả về Exam mới hoặc đã chỉnh sửa
    public TestManagementPanel.Exam getNewExam() {
        return newExam;
    }

    // Main để kiểm tra giao diện
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TestManagementPanel panel = new TestManagementPanel();
            List<TestManagementPanel.Exam> examList = new ArrayList<>();
            DialogAddTest dialog = new DialogAddTest(panel, null, examList);
            dialog.setVisible(true);
        });
    }
}