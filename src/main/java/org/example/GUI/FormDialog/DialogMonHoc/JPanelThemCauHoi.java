package org.example.GUI.FormDialog.DialogMonHoc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.example.BUS.QuestionBUS;
import org.example.DAO.AnswersDAO;
import org.example.DAO.QuestionDAO;
import org.example.DAO.TopicDAO;
import org.example.DTO.AnswersDTO;
import org.example.DTO.QuestionDTO;
import org.example.DTO.TopicsDTO;

public class JPanelThemCauHoi extends JPanel {
    private JPanel contentPanel;
    private JPanel previousPanel;
    private DefaultTableModel tableModel;
 // Khai báo ComboBox trên toàn cục
    private JComboBox<String> cboTopic;
    private List<Integer> topicIDs; // Lưu danh sách ID của topic
    private JComboBox<String> cboCorrectAnswer;

    public JPanelThemCauHoi(JPanel contentPanel, JPanel previousPanel, DefaultTableModel tableModel) {
        this.contentPanel = contentPanel;
        this.previousPanel = previousPanel;
        this.tableModel = tableModel; // Truyền từ Form chính vào
        initComponents();
    }

    private void initComponents() {
    	setBackground(new Color(30, 30, 30));
        setLayout(null);
        setPreferredSize(new Dimension(900, 500)); // Phóng to hơn

        // Tiêu đề
        JLabel lblTitle = new JLabel("Thêm câu hỏi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Tăng font chữ
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(350, 10, 300, 30); // Căn giữa tiêu đề
        add(lblTitle);

        // ID Câu hỏi
        JLabel lblID = new JLabel("ID Câu hỏi");
        lblID.setForeground(Color.WHITE);
        lblID.setBounds(50, 60, 100, 25);
        add(lblID);
        JTextField txtID = new JTextField();
        txtID.setBounds(150, 60, 200, 30);
        txtID.setEditable(false); // Không cho phép chỉnh sửa
        txtID.setBackground(Color.LIGHT_GRAY); // Làm nổi bật
        txtID.setText(String.valueOf(new QuestionDAO().getNextQuestionID())); // Gán ID tự động ngay khi mở form
        add(txtID);
     // Chọn đáp án đúng
        JLabel lblCorrectAnswer = new JLabel("Đáp án đúng");
        lblCorrectAnswer.setForeground(Color.WHITE);
        lblCorrectAnswer.setBounds(50, 350, 100, 25);
        add(lblCorrectAnswer);

        cboCorrectAnswer = new JComboBox<>(new String[]{"A", "B", "C", "D"});
        cboCorrectAnswer.setBounds(150, 350, 100, 30);
        add(cboCorrectAnswer);

        // Loại câu hỏi (ComboBox)
        JLabel lblLoai = new JLabel("Loại câu hỏi");
        lblLoai.setForeground(Color.WHITE);
        lblLoai.setBounds(400, 60, 100, 25);
        add(lblLoai);

        JComboBox<String> cboLoai = new JComboBox<>(new String[] { "Easy", "Medium" });
        cboLoai.setBounds(500, 60, 100, 30);
        add(cboLoai);

        // Nhập câu hỏi
        JLabel lblCauHoi = new JLabel("Câu hỏi");
        lblCauHoi.setForeground(Color.WHITE);
        lblCauHoi.setBounds(50, 110, 100, 25);
        add(lblCauHoi);

        JTextField txtCauHoi = new JTextField();
        txtCauHoi.setBounds(150, 110, 600, 30);
        txtCauHoi.setBackground(Color.LIGHT_GRAY);
        add(txtCauHoi);

        // Nhập đáp án
        JLabel lblDapAn = new JLabel("Đáp Án");
        lblDapAn.setForeground(Color.WHITE);
        lblDapAn.setBounds(50, 160, 100, 25);
        add(lblDapAn);
        JTextField txtA = new JTextField();
        txtA.setBounds(130, 190, 600, 30);
        add(txtA);
        JTextField txtB = new JTextField();
        txtB.setBounds(130, 230, 600, 30);
        add(txtB);
        JTextField txtC = new JTextField();
        txtC.setBounds(130, 270, 600, 30);
        add(txtC);
        JTextField txtD = new JTextField();
        txtD.setBounds(130, 310, 600, 30);
        add(txtD);

        // Nút thoát
        JButton btnThoat = new JButton("Thoát");
        btnThoat.setBounds(250, 380, 150, 40); // Tăng kích thước
        btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnThoat.addActionListener(e -> returnToMain());
        add(btnThoat);
        JLabel lblNewLabel = new JLabel("Tên Topic");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(659, 66, 77, 13);
        add(lblNewLabel);
     // Lấy danh sách chủ đề từ cơ sở dữ liệu
        topicIDs = new ArrayList<>();
        cboTopic = new JComboBox<>();
        cboTopic.setBounds(729, 62, 120, 30);
        add(cboTopic);

        // Nạp dữ liệu vào ComboBox
        loadTopics();



        JButton btnChapNhan = new JButton("Chấp nhận");
        btnChapNhan.setBounds(500, 380, 150, 40);
        btnChapNhan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnChapNhan.setBackground(Color.RED);
        btnChapNhan.setForeground(Color.WHITE);
        add(btnChapNhan);

        btnChapNhan.addActionListener(e -> {
            txtID.setEditable(false); // Không cho phép nhập ID bằng tay
            int questionID = new QuestionDAO().getNextQuestionID(); // Lấy ID tự động
            txtID.setText(String.valueOf(questionID)); // Gán vào ô ID

            String content = txtCauHoi.getText().trim();
            String level = (String) cboLoai.getSelectedItem();
            String answerA = txtA.getText().trim();
            String answerB = txtB.getText().trim();
            String answerC = txtC.getText().trim();
            String answerD = txtD.getText().trim();
            boolean status = true;

            if (content.isEmpty() || answerA.isEmpty() || answerB.isEmpty() || answerC.isEmpty() || answerD.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int topicID = getSelectedTopicID(); // Lấy topic từ giao diện
                QuestionDTO question = new QuestionDTO(questionID, content, "A", topicID, level, status);
                QuestionBUS questionBUS = new QuestionBUS();
                boolean isInserted = questionBUS.insertQuestion(question);

                if (isInserted) {
                    // Chèn câu trả lời vào database
                    AnswersDAO answersDAO = new AnswersDAO();

                    // Đáp án đúng (có thể lấy từ radio button nếu có)
                    String correctAnswer = getCorrectAnswer(); // Hàm lấy đáp án đúng (cần thêm)

                    String defaultImage = "no_image.png"; // Hoặc một đường dẫn ảnh mặc định

                    AnswersDTO ansA = new AnswersDTO(0, questionID, answerA, defaultImage, correctAnswer.equals("A"), true);
                    AnswersDTO ansB = new AnswersDTO(0, questionID, answerB, defaultImage, correctAnswer.equals("B"), true);
                    AnswersDTO ansC = new AnswersDTO(0, questionID, answerC, defaultImage, correctAnswer.equals("C"), true);
                    AnswersDTO ansD = new AnswersDTO(0, questionID, answerD, defaultImage, correctAnswer.equals("D"), true);


                    answersDAO.insertAnswer(ansA);
                    answersDAO.insertAnswer(ansB);
                    answersDAO.insertAnswer(ansC);
                    answersDAO.insertAnswer(ansD);

                    JOptionPane.showMessageDialog(this, "Thêm câu hỏi thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    loadDataFromDatabase(); // Cập nhật lại bảng
                    returnToMain(); // Quay về giao diện chính
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm câu hỏi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm câu hỏi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    private void loadTopics() {
        TopicDAO topicDAO = new TopicDAO();
        List<TopicsDTO> topics = topicDAO.getAllTopics() ;// Lấy danh sách từ database
        cboTopic.removeAllItems(); // Xóa dữ liệu cũ

        for (TopicsDTO topic : topics) {
            cboTopic.addItem(topic.getTpTitle()); // Hiển thị tên topic
            topicIDs.add(topic.getTopicID()); // Lưu ID tương ứng
        }
    }
    private int getSelectedTopicID() {
        int selectedIndex = cboTopic.getSelectedIndex();
        if (selectedIndex >= 0) {
            return topicIDs.get(selectedIndex); // Lấy ID theo vị trí đã chọn
        }
        return -1; // Trả về -1 nếu không chọn gì
    }
    private String getCorrectAnswer() {
        return (String) cboCorrectAnswer.getSelectedItem();
    }



    private void returnToMain() {
        if (contentPanel != null && previousPanel != null) {
            contentPanel.removeAll();
            contentPanel.add(previousPanel);
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    private void loadDataFromDatabase() {
//    	int idTopic = Integer.parseInt(Topic);
        tableModel.setRowCount(0);
        QuestionDAO questionDAO = new QuestionDAO();
        List<QuestionDTO> questions = questionDAO.getQuestionsByTopic(getSelectedTopicID());
        for (QuestionDTO q : questions) {
            tableModel.addRow(new Object[]{q.getQuestionID(), q.getQContent(), q.getQLevel(), q.getQStatus() ? "Hoạt động" : "Ẩn"});
        }
    }
}
