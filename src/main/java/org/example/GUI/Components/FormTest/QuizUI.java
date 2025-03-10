package org.example.GUI.Components.FormTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
    private JLabel questionLabel, levelLabel, timeLabel, totalQuestionLabel;
    private JRadioButton[] answerButtons;
    private JButton submitButton, nextButton;
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
    private UsersDTO currentUser;
    private int initialTime;
    private JPanel contentPanel;

    public QuizUI(String testCode, JPanel contentPanel) {
        this.testCode = testCode;
        this.contentPanel = contentPanel;
        this.quizBUS = new QuizBUS();
        this.currentUser = SessionManager.getCurrentUser();
        System.out.println(currentUser);
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Lỗi: Không tìm thấy thông tin người dùng!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        initComponents();

        QuizDAO quizDAO = new QuizDAO();
        questions = quizDAO.getQuestionsByTestCode(testCode);
        totalQuestions = questions.size();

        TestDAO testDAO = new TestDAO();
        TestDTO testInfo = testDAO.getTestByCode(testCode);
        remainingTime = (testInfo != null) ? testInfo.getTestTime() * 60 : 600;
        initialTime = remainingTime;

        if (!questions.isEmpty()) {
            loadQuestion(currentIndex);
        }
        startCountdown();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 20));
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(52, 152, 219), 0, getHeight(), new Color(41, 128, 185)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        add(headerPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Làm Bài Kiểm Tra");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BorderLayout(0, 15));
        add(contentPanel, BorderLayout.CENTER);

        JPanel quizPanel = new JPanel();
        quizPanel.setOpaque(false);
        quizPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JScrollPane scrollPane = new JScrollPane(quizPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        questionLabel = new JLabel("", JLabel.LEFT);
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        questionLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        quizPanel.add(questionLabel, gbc);

        levelLabel = new JLabel("", JLabel.RIGHT);
        levelLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        levelLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        quizPanel.add(levelLabel, gbc);

        answerGroup = new ButtonGroup();
        answerButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JRadioButton();
            answerButtons[i].setFont(new Font("Segoe UI", Font.PLAIN, 16));
            answerButtons[i].setForeground(new Color(44, 62, 80));
            answerButtons[i].setBackground(Color.WHITE);
            answerButtons[i].setOpaque(true);
            answerButtons[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)));
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 3;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            answerGroup.add(answerButtons[i]);
            quizPanel.add(answerButtons[i], gbc);

            final int idx = i;
            answerButtons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    answerButtons[idx].setBackground(new Color(245, 248, 255));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    answerButtons[idx].setBackground(Color.WHITE);
                }
            });
        }

        timeLabel = new JLabel("Thời gian: 00:00");
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        timeLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        quizPanel.add(timeLabel, gbc);

        totalQuestionLabel = new JLabel("Số lượng câu hỏi: " + totalQuestions);
        totalQuestionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalQuestionLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        quizPanel.add(totalQuestionLabel, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton = createStyledButton("Nộp bài", new Color(52, 152, 219), Color.WHITE);
        submitButton.addActionListener(e -> submitQuiz());
        buttonPanel.add(submitButton);

        nextButton = createStyledButton("Next", new Color(231, 76, 60), Color.WHITE);
        nextButton.addActionListener(e -> loadNextQuestion());
        buttonPanel.add(nextButton);
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

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
        userAnswers.clear();
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
                userAnswers.add(-1);
            }
        }
    }

    private void submitQuiz() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }

        saveUserAnswer();

        int correctCount = 0;
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
        boolean success = quizBUS.saveQuizResult(currentUser.getUserID(), testCode, questions, userAnswers, score,
                today);

        int timeTakenSeconds = initialTime - remainingTime;
        String timeTaken = String.format("%d:%02d", timeTakenSeconds / 60, timeTakenSeconds % 60);

        ResultForm resultForm = new ResultForm(contentPanel, success, score, correctCount, totalQuestions, currentUser,
                testCode, initialTime, remainingTime, timeTaken);
        resultForm.setVisible(true);
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