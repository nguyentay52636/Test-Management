package org.example.GUI.Components.FormTest;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.example.BUS.QuizBUS;
import org.example.DAO.QuizDAO;
import org.example.DAO.TestDAO;
import org.example.DTO.AnswersDTO;
import org.example.DTO.QuestionDTO;
import org.example.DTO.SessionManager;
import org.example.DTO.TestDTO;
import org.example.DTO.UsersDTO;

public class QuizUI extends JPanel {
    private JLabel questionLabel, levelLabel;
    private JRadioButton[] answerButtons;
    private JButton submitButton, nextButton;
    private JLabel timeLabel, totalQuestionLabel;
    private ButtonGroup answerGroup;
    private List<QuestionDTO> questions;
    private List<AnswersDTO> answers;
    private int currentIndex = 0;
    private int totalQuestions = 0;
    private Timer countdownTimer;
    private int remainingTime;
    private String testCode;
    private List<Integer> userAnswers = new ArrayList<>();
    private QuizBUS quizBUS;
    private UsersDTO currentUser; // Lưu thông tin người dùng hiện tại

    public QuizUI(String testCode) {
        this.testCode = testCode;
        this.quizBUS = new QuizBUS();
        this.currentUser = SessionManager.getCurrentUser(); // Lấy thông tin user từ SessionManager
        System.out.println(currentUser);
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không tìm thấy thông tin người dùng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        setLayout(null);
        setBackground(new Color(50, 50, 60));

        QuizDAO quizDAO = new QuizDAO();
        questions = quizDAO.getQuestionsByTestCode(testCode);
        totalQuestions = questions.size();

        TestDAO testDAO = new TestDAO();
        TestDTO testInfo = testDAO.getTestByCode(testCode);
        remainingTime = (testInfo != null) ? testInfo.getTestTime() * 60 : 600;

        questionLabel = new JLabel("", SwingConstants.LEFT);
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBounds(20, 20, 600, 40);
        add(questionLabel);

        levelLabel = new JLabel("", SwingConstants.RIGHT);
        levelLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setBounds(650, 20, 150, 30);
        add(levelLabel);

        answerGroup = new ButtonGroup();
        answerButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JRadioButton();
            answerButtons[i].setFont(new Font("Segoe UI", Font.PLAIN, 16));
            answerButtons[i].setForeground(Color.WHITE);
            answerButtons[i].setBackground(new Color(50, 50, 60));
            answerButtons[i].setBounds(50, 80 + i * 40, 700, 30);
            answerGroup.add(answerButtons[i]);
            add(answerButtons[i]);
        }

        timeLabel = new JLabel("Thời gian: 00:00", SwingConstants.LEFT);
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(50, 250, 150, 30);
        add(timeLabel);

        totalQuestionLabel = new JLabel("Số lượng câu hỏi: " + totalQuestions, SwingConstants.RIGHT);
        totalQuestionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalQuestionLabel.setForeground(Color.WHITE);
        totalQuestionLabel.setBounds(600, 250, 200, 30);
        add(totalQuestionLabel);

        submitButton = new JButton("Nộp bài");
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        submitButton.setBounds(100, 300, 120, 40);
        add(submitButton);
        submitButton.addActionListener(e -> submitQuiz());

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nextButton.setBackground(Color.RED);
        nextButton.setForeground(Color.WHITE);
        nextButton.setBounds(600, 300, 120, 40);
        add(nextButton);
        nextButton.addActionListener(e -> loadNextQuestion());

        if (!questions.isEmpty()) {
            loadQuestion(currentIndex);
        }

        startCountdown();
    }

    private void loadQuestion(int index) {
        QuestionDTO question = questions.get(index);
        questionLabel.setText("Câu " + (index + 1) + ": " + question.getQContent());
        levelLabel.setText("Mức độ: " + question.getQLevel());

        answers = quizBUS.getAnswersByQuestionID(question.getQuestionID());

        answerGroup.clearSelection();
        for (int i = 0; i < answerButtons.length; i++) {
            if (i < answers.size()) {
                answerButtons[i].setText(answers.get(i).getAwContent());
                answerButtons[i].setVisible(true);
                answerButtons[i].setActionCommand(String.valueOf(answers.get(i).getAwID()));
            } else {
                answerButtons[i].setVisible(false);
            }
        }
    }

    private void loadNextQuestion() {
        saveUserAnswer();
        if (currentIndex < totalQuestions - 1) {
            currentIndex++;
            loadQuestion(currentIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã hoàn thành bài kiểm tra!");
        }
    }

    private void saveUserAnswer() {
        userAnswers.clear(); // Đảm bảo danh sách không bị lặp dữ liệu cũ
    
        for (int i = 0; i < questions.size(); i++) {
            boolean answered = false;
            for (JRadioButton btn : answerButtons) {
                if (btn.isSelected() && Integer.parseInt(btn.getActionCommand()) == questions.get(i).getQuestionID()) {
                    userAnswers.add(Integer.parseInt(btn.getActionCommand()));
                    answered = true;
                    break;
                }
            }
            if (!answered) {
                userAnswers.add(-1); // Nếu không chọn đáp án nào
            }
        }
    }
    

    private void submitQuiz() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
    
        saveUserAnswer(); // Đảm bảo danh sách userAnswers đầy đủ
    
        int correctCount = 0;
    
        // Kiểm tra kích thước userAnswers trước khi truy cập
        if (userAnswers.size() < questions.size()) {
            JOptionPane.showMessageDialog(this, "Lỗi: Dữ liệu câu trả lời không đủ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        for (int i = 0; i < questions.size(); i++) {
            int correctAnswerID = quizBUS.getCorrectAnswerByQuestionID(questions.get(i).getQuestionID());
    
            if (userAnswers.get(i) == correctAnswerID) {
                correctCount++;
            }
        }

        double score = ((double) correctCount / questions.size()) * 10.0;
        LocalDate today = LocalDate.now();
    
        boolean success = quizBUS.saveQuizResult(currentUser.getUserID(), testCode, questions, userAnswers, score, today);
    
        if (success) {
            JOptionPane.showMessageDialog(this, "Bài kiểm tra đã được nộp!\nĐiểm của bạn: " + score);
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi khi lưu kết quả!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void startCountdown() {
        countdownTimer = new Timer(1000, e -> {
            if (remainingTime > 0) {
                remainingTime--;
                int minutes = remainingTime / 60;
                int seconds = remainingTime % 60;
                timeLabel.setText(String.format("Thời gian: %02d:%02d", minutes, seconds));
            } else {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(this, "Hết giờ! Bài kiểm tra sẽ tự động nộp.");
                submitQuiz();
            }
        });

        countdownTimer.start();
    }
}
