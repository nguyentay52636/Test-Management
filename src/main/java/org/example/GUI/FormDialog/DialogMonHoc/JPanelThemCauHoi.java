package org.example.GUI.FormDialog.DialogMonHoc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
    private JComboBox<String> cboTopic; // ComboBox for topics
    private List<Integer> topicIDs; // Store topic IDs
    private JComboBox<String> cboCorrectAnswer; // ComboBox for correct answer

    public JPanelThemCauHoi(JPanel contentPanel, JPanel previousPanel,DefaultTableModel tableModel) {
    	this.tableModel = tableModel; 
        this.contentPanel = contentPanel;
        this.previousPanel = previousPanel;
        this.topicIDs = new ArrayList<>(); // Initialize topic IDs list
        initComponents();
 // Load topics into ComboBox
    }

    private void initComponents() {
        setBackground(new Color(240, 242, 245)); // Light, professional background
        setLayout(null);
        setPreferredSize(new Dimension(900, 550)); // Slightly larger for better spacing
        setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2)); // Dialog-like border

        // Header Section
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 123, 255)); // Professional blue header
        headerPanel.setBounds(0, 0, 900, 60);
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel lblTitle = new JLabel("Thêm Câu Hỏi Mới");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 15, 300, 30);
        headerPanel.add(lblTitle);

        // Content Section
        JLabel lblID = new JLabel("ID Câu Hỏi:");
        lblID.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblID.setForeground(new Color(51, 51, 51));
        lblID.setBounds(50, 80, 100, 25);
        add(lblID);

        JTextField txtID = new JTextField(String.valueOf(new QuestionDAO().getNextQuestionID()));
        txtID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtID.setBackground(Color.WHITE);
        txtID.setForeground(new Color(51, 51, 51));
        txtID.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtID.setBounds(150, 80, 200, 35);
        txtID.setEditable(false); // ID is auto-generated and non-editable
        add(txtID);

        JLabel lblLoai = new JLabel("Mức Độ:");
        lblLoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblLoai.setForeground(new Color(51, 51, 51));
        lblLoai.setBounds(400, 80, 100, 25);
        add(lblLoai);

        JComboBox<String> cboLoai = new JComboBox<>(new String[] { "Dễ", "Trung bình", "Khó" });
        cboLoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboLoai.setBackground(Color.WHITE);
        cboLoai.setForeground(new Color(51, 51, 51));
        cboLoai.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        cboLoai.setBounds(500, 80, 150, 35);
        add(cboLoai);

        // Topic Selection
        JLabel lblTopic = new JLabel("Chủ Đề:");
        lblTopic.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblTopic.setForeground(new Color(51, 51, 51));
        lblTopic.setBounds(50, 130, 100, 25);
        add(lblTopic);

        cboTopic = new JComboBox<>();
        cboTopic.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboTopic.setBackground(Color.WHITE);
        cboTopic.setForeground(new Color(51, 51, 51));
        cboTopic.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        cboTopic.setBounds(150, 130, 200, 35);
        add(cboTopic);

        JLabel lblCauHoi = new JLabel("Nội Dung Câu Hỏi:");
        lblCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCauHoi.setForeground(new Color(51, 51, 51));
        lblCauHoi.setBounds(50, 180, 150, 25);
        add(lblCauHoi);

        JTextField txtCauHoi = new JTextField();
        txtCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCauHoi.setBackground(Color.WHITE);
        txtCauHoi.setForeground(new Color(51, 51, 51));
        txtCauHoi.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtCauHoi.setBounds(200, 180, 650, 35);
        add(txtCauHoi);

        // Answer Section
        JLabel lblDapAn = new JLabel("Đáp Án:");
        lblDapAn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDapAn.setForeground(new Color(51, 51, 51));
        lblDapAn.setBounds(50, 230, 100, 25);
        add(lblDapAn);

        JCheckBox chkA = new JCheckBox("A");
        chkA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkA.setBackground(new Color(240, 242, 245));
        chkA.setBounds(80, 260, 50, 25);
        add(chkA);
        JTextField txtA = new JTextField();
        txtA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtA.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtA.setBounds(150, 260, 700, 35);
        add(txtA);

        JCheckBox chkB = new JCheckBox("B");
        chkB.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkB.setBackground(new Color(240, 242, 245));
        chkB.setBounds(80, 310, 50, 25);
        add(chkB);
        JTextField txtB = new JTextField();
        txtB.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtB.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtB.setBounds(150, 310, 700, 35);
        add(txtB);

        JCheckBox chkC = new JCheckBox("C");
        chkC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkC.setBackground(new Color(240, 242, 245));
        chkC.setBounds(80, 360, 50, 25);
        add(chkC);
        JTextField txtC = new JTextField();
        txtC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtC.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtC.setBounds(150, 360, 700, 35);
        add(txtC);

        JCheckBox chkD = new JCheckBox("D");
        chkD.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkD.setBackground(new Color(240, 242, 245));
        chkD.setBounds(80, 410, 50, 25);
        add(chkD);
        JTextField txtD = new JTextField();
        txtD.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtD.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtD.setBounds(150, 410, 700, 35);
        add(txtD);

        // Correct Answer Selection
        JLabel lblCorrectAnswer = new JLabel("Đáp Án Đúng:");
        lblCorrectAnswer.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCorrectAnswer.setForeground(new Color(51, 51, 51));
        lblCorrectAnswer.setBounds(400, 130, 100, 25);
        add(lblCorrectAnswer);

        cboCorrectAnswer = new JComboBox<>(new String[] { "A", "B", "C", "D" });
        cboCorrectAnswer.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboCorrectAnswer.setBackground(Color.WHITE);
        cboCorrectAnswer.setForeground(new Color(51, 51, 51));
        cboCorrectAnswer.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        cboCorrectAnswer.setBounds(500, 130, 150, 35);
        add(cboCorrectAnswer);

        // Footer Section (Buttons)
        JButton btnThoat = createStyledButton("Hủy", new Color(108, 117, 125), "Hủy và quay lại");
        btnThoat.setBounds(250, 470, 150, 45);
        btnThoat.addActionListener(e -> returnToMain());
        add(btnThoat);
        JButton btnChapNhan = createStyledButton("Xác Nhận", new Color(40, 167, 69), "Thêm câu hỏi vào hệ thống");
        btnChapNhan.setBounds(500, 470, 150, 45);
        btnChapNhan.addActionListener(
                e -> addQuestion(txtID, cboTopic, cboLoai, txtCauHoi, txtA, txtB, txtC, txtD, cboCorrectAnswer));
        add(btnChapNhan);
        loadTopics();
    }

    private JButton createStyledButton(String text, Color bgColor, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        button.setToolTipText(tooltip);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }
        });

        return button;
    }

    private void loadTopics() {
        TopicDAO topicDAO = new TopicDAO();
        List<TopicsDTO> topics = topicDAO.getAllTopics(); // Fetch all topics from database
        cboTopic.removeAllItems(); // Clear existing items

        for (TopicsDTO topic : topics) {
            cboTopic.addItem(topic.getTpTitle()); // Add topic title to ComboBox
            topicIDs.add(topic.getTopicID()); // Store corresponding ID
        }
    }
    private int getSelectedTopicID() {
        int selectedIndex = cboTopic.getSelectedIndex();
        if (selectedIndex >= 0) {
            return topicIDs.get(selectedIndex); // Return the topic ID for the selected title
        }
        return -1; // Return -1 if no selection
    }

    private void addQuestion(JTextField txtID, JComboBox<String> cboTopic, JComboBox<String> cboLoai,
            JTextField txtCauHoi,
            JTextField txtA, JTextField txtB, JTextField txtC, JTextField txtD, JComboBox<String> cboCorrectAnswer) {
        String content = txtCauHoi.getText().trim();
        String level = (String) cboLoai.getSelectedItem();
        String answerA = txtA.getText().trim();
        String answerB = txtB.getText().trim();
        String answerC = txtC.getText().trim();
        String answerD = txtD.getText().trim();
        String correctAnswer = (String) cboCorrectAnswer.getSelectedItem();
        int topicID = getSelectedTopicID();
        boolean status = true;
        if (content.isEmpty() || answerA.isEmpty() || answerB.isEmpty() || answerC.isEmpty() || answerD.isEmpty()
                || topicID == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin và chọn chủ đề!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int questionID = Integer.parseInt(txtID.getText().trim());
            QuestionDTO question = new QuestionDTO(questionID, content, "A", topicID, level, status);
            // support yet
            QuestionBUS questionBUS = new QuestionBUS();

            if (questionBUS.insertQuestion(question)) {
            	
                AnswersDAO answersDAO = new AnswersDAO();
                String defaultImage = "no_image.png"; // Default image path

                AnswersDTO ansA = new AnswersDTO(0, questionID, answerA, defaultImage, correctAnswer.equals("A"), true);
                AnswersDTO ansB = new AnswersDTO(0, questionID, answerB, defaultImage, correctAnswer.equals("B"), true);
                AnswersDTO ansC = new AnswersDTO(0, questionID, answerC, defaultImage, correctAnswer.equals("C"), true);
                AnswersDTO ansD = new AnswersDTO(0, questionID, answerD, defaultImage, correctAnswer.equals("D"), true);

                answersDAO.insertAnswer(ansA);
                answersDAO.insertAnswer(ansB);
                answersDAO.insertAnswer(ansC);
                answersDAO.insertAnswer(ansD);

                JOptionPane.showMessageDialog(this, "Thêm câu hỏi thành công!", "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
                loadDataFromDatabase();
                returnToMain();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm câu hỏi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID câu hỏi phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm câu hỏi: " + ex.getMessage(), "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
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