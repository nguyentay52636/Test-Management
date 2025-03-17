package org.example.GUI.FormDialog.DialogTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.example.DAO.ExamDAO;
import org.example.DAO.QuizDAO;
import org.example.DAO.ResultDAO;
import org.example.DAO.TestDAO;
import org.example.DAO.TopicDAO;
import org.example.DTO.ExamsDTO;
import org.example.DTO.QuestionDTO;
import org.example.DTO.ResultDTO;
import org.example.DTO.TestDTO;
import org.example.DTO.Test_structureDTO;
import org.example.DTO.TopicsDTO;
import org.example.GUI.Components.FormTest.TestManagementPanel;

public class DialogAddTest extends JDialog {
    private JTextField titleField, testCodeField, dateField, easyQuestionField, mediumQuestionField, hardQuestionField, durationField, attemptsField;
    
    private TestManagementPanel parentPanel;
    private JComboBox<String> cboTopic; // ComboBox for topics
    private List<Integer> topicIDs;
    private List<TestManagementPanel.Exam> examList;

    public DialogAddTest(TestManagementPanel parentPanel, List<TestManagementPanel.Exam> examList) {
        super((Frame) SwingUtilities.getWindowAncestor(parentPanel), "Thêm Kỳ Thi", true);
        this.parentPanel = parentPanel;
        this.examList = examList;
        this.topicIDs = new ArrayList<>(); 
        initializeUI();
    }

    private void initializeUI() {
        setSize(750, 500); // Tăng kích thước cửa sổ
        setLocationRelativeTo(parentPanel);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        inputPanel.add(createLabel("Tên kỳ thi:", labelFont), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        titleField = createTextField(fieldFont);
        inputPanel.add(titleField, gbc);

     // Chủ đề
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(createLabel("Chủ đề:", labelFont), gbc);
        gbc.gridx = 1;
        cboTopic = new JComboBox<>(new String[]{"Toán", "Lý", "Hóa", "Sinh", "Văn"});
        cboTopic.setFont(fieldFont);
        inputPanel.add(cboTopic, gbc);

        // Mã kỳ thi (Đưa xuống dưới chủ đề)
        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(createLabel("Mã kỳ thi:", labelFont), gbc);
        gbc.gridx = 1;
        testCodeField = createTextField(fieldFont);
        inputPanel.add(testCodeField, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(createLabel("Ngày thi (YYYY-MM-DD):", labelFont), gbc);
        gbc.gridx = 1;
        dateField = createTextField(fieldFont);
        inputPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(createLabel("Số câu dễ:", labelFont), gbc);
        gbc.gridx = 1;
        easyQuestionField = createTextField(fieldFont);
        inputPanel.add(easyQuestionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(createLabel("Số câu trung bình:", labelFont), gbc);
        gbc.gridx = 1;
        mediumQuestionField = createTextField(fieldFont);
        inputPanel.add(mediumQuestionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(createLabel("Số câu khó:", labelFont), gbc);
        gbc.gridx = 1;
        hardQuestionField = createTextField(fieldFont);
        inputPanel.add(hardQuestionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(createLabel("Thời gian làm bài (phút):", labelFont), gbc);
        gbc.gridx = 1;
        durationField = createTextField(fieldFont);
        inputPanel.add(durationField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(createLabel("Số lần thi:", labelFont), gbc);
        gbc.gridx = 1;
        attemptsField = createTextField(fieldFont);
        inputPanel.add(attemptsField, gbc);
        loadTopics();
        // Nút Lưu & Hủy
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnSave = createButton("Lưu", new Color(0, 123, 255));
        JButton btnCancel = createButton("Hủy", new Color(220, 53, 69));

        btnSave.addActionListener(e -> saveExam());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private JTextField createTextField(Font font) {
        JTextField field = new JTextField(20);
        field.setFont(font);
        return field;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 35));
        return button;
    }

    private void saveExam() {
        String title = titleField.getText().trim();
        String testCode = testCodeField.getText().trim();
        String topic = (String) cboTopic.getSelectedItem();
        String date = dateField.getText().trim();
        String easy = easyQuestionField.getText().trim();
        String medium = mediumQuestionField.getText().trim();
        String hard = hardQuestionField.getText().trim();
        String duration = durationField.getText().trim();
        String attempts = attemptsField.getText().trim();

        if (title.isEmpty() || testCode.isEmpty() || date.isEmpty() || easy.isEmpty() || 
            medium.isEmpty() || hard.isEmpty() || duration.isEmpty() || attempts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Ngày thi phải có định dạng YYYY-MM-DD!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Test_structureDTO testT = new Test_structureDTO();
        TestDTO test = new TestDTO();
        ExamsDTO examsDTO = new ExamsDTO();
        TestDAO testDAO = new TestDAO();
        
        testT.setTestCode(testCode);
        int selectedTopicID = getSelectedTopicID();
        testT.setTpID(selectedTopicID);

        testT.setNumberEasy(Integer.parseInt(easy));
        testT.setNumberMedium(Integer.parseInt(medium));
        testT.setNumberDiff(Integer.parseInt(hard));
        testDAO.insertTestStructure(testT);

        test.setTestCode(testCode);
        test.setTestLimit(Integer.parseInt(attempts));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng chuẩn
            java.util.Date utilDate = sdf.parse(date); // Chuyển từ String thành java.util.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Chuyển thành java.sql.Date
            test.setDate(sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        test.setTitle(title);
        test.setTestStatus(1);
        test.setTestTime(Integer.parseInt(duration));
        testDAO.insertTest(test);

        char exOrder = (char) ('A' + new Random().nextInt(6));
        String temp = Character.toString(exOrder);
        String exCode = testCode + exOrder;

        List<Integer> arraysEasy = new ArrayList<>();
        List<Integer> arraysMedium = new ArrayList<>();
        List<Integer> arraysDiff = new ArrayList<>();
        List<Integer> arraysQuestion = new ArrayList<>();

        QuizDAO questionDAO = new QuizDAO();
        arraysEasy = questionDAO.getQuestionIdsByLevel(testCode, "Easy", Integer.parseInt(easy));
        arraysMedium = questionDAO.getQuestionIdsByLevel(testCode, "Medium", Integer.parseInt(medium));
        arraysDiff = questionDAO.getQuestionIdsByLevel(testCode, "Diff", Integer.parseInt(hard));

        // Gộp danh sách câu hỏi vào mảng chính
        arraysQuestion.addAll(arraysEasy);
        arraysQuestion.addAll(arraysMedium);
        arraysQuestion.addAll(arraysDiff);

        ExamDAO examDAO = new ExamDAO();
        examsDTO.setTestCode(testCode);
        examsDTO.setExOrder(temp);
        examsDTO.setExCode(exCode);

        // Chuyển danh sách ID thành chuỗi, cách nhau bởi dấu phẩy
        String questionIdsString = arraysQuestion.stream()
                                                 .map(String::valueOf)
                                                 .collect(Collectors.joining(", "));
        
        examsDTO.setExQuestionIDs(questionIdsString);
        examDAO.createExam(examsDTO);

        JOptionPane.showMessageDialog(this, "Đã thêm kỳ thi mới!", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
        parentPanel.loadExamDataFromDB();
        dispose();
    }

    public TestManagementPanel.Exam getNewExam() {
        if (examList == null || examList.isEmpty()) {
            return null; // Tránh gọi trên danh sách rỗng/null
        }
        return examList.get(examList.size() - 1);
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
        if (selectedIndex >= 0 && selectedIndex < topicIDs.size()) {
            return topicIDs.get(selectedIndex);
        }
        JOptionPane.showMessageDialog(this, "Chưa chọn chủ đề!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return -1;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TestManagementPanel panel = new TestManagementPanel();
            List<TestManagementPanel.Exam> examList = new ArrayList<>();
            DialogAddTest dialog = new DialogAddTest(panel, examList);
            dialog.setVisible(true);
        });
    }
}
