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
import javax.swing.border.EmptyBorder;

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
    private List<Integer> userAnswers;
    private QuizBUS quizBUS;
    private UsersDTO currentUser;
    private int initialTime;
    private JPanel contentPanel;

    public QuizUI(String testCode, JPanel contentPanel) {
        this.testCode = testCode;
        this.contentPanel = contentPanel;
        this.quizBUS = new QuizBUS();
        this.currentUser = SessionManager.getCurrentUser();
        this.userAnswers = new ArrayList<>(10);

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

        for (int i = 0; i < totalQuestions; i++) {
            userAnswers.add(-1);
        }

        if (!questions.isEmpty()) {
            loadQuestion(currentIndex);
        }
        startCountdown();
    }

    private void initComponents() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(240, 242, 245)); // Nền nhẹ nhàng, chuyên nghiệp
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header Panel with Gradient
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(52, 152, 219), getWidth(), getHeight(),
                        new Color(41, 128, 185)); // Gradient xéo
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 100));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        add(headerPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Bài Kiểm Tra");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        timeLabel = new JLabel("Thời gian: 00:00");
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        timeLabel.setForeground(Color.WHITE);
        headerPanel.add(timeLabel);

        // Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout(0, 15));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(contentPanel, BorderLayout.CENTER);

        JPanel quizPanel = new JPanel(new GridBagLayout());
        quizPanel.setBackground(Color.WHITE); // Nền trắng cho nội dung
        quizPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 223, 230), 1, true),
                new EmptyBorder(20, 20, 20, 20)));

        JScrollPane scrollPane = new JScrollPane(quizPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        questionLabel = new JLabel("", JLabel.LEFT);
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        questionLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        quizPanel.add(questionLabel, gbc);

        levelLabel = new JLabel("", JLabel.RIGHT);
        levelLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        levelLabel.setForeground(new Color(108, 117, 125));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        quizPanel.add(levelLabel, gbc);

        answerGroup = new ButtonGroup();
        answerButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JRadioButton();
            answerButtons[i].setFont(new Font("Segoe UI", Font.PLAIN, 18));
            answerButtons[i].setForeground(new Color(44, 62, 80));
            answerButtons[i].setBackground(Color.WHITE);
            answerButtons[i].setOpaque(true);
            answerButtons[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 223, 230), 1, true),
                    new EmptyBorder(10, 15, 10, 15)));
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 3;
            answerGroup.add(answerButtons[i]);
            quizPanel.add(answerButtons[i], gbc);

            final int idx = i;
            answerButtons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    answerButtons[idx].setBackground(new Color(240, 248, 255));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    answerButtons[idx].setBackground(Color.WHITE);
                }
            });
        }

        totalQuestionLabel = new JLabel("Số câu hỏi: " + totalQuestions);
        totalQuestionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalQuestionLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.EAST;
        quizPanel.add(totalQuestionLabel, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton = createStyledButton("Nộp Bài", new Color(52, 152, 219));
        submitButton.addActionListener(e -> submitQuiz());
        buttonPanel.add(submitButton);

        nextButton = createStyledButton("Tiếp Theo", new Color(46, 204, 113));
        nextButton.addActionListener(e -> loadNextQuestion());
        buttonPanel.add(nextButton);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(140, 50));
        button.setBorder(new EmptyBorder(10, 15, 10, 15));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
                button.setBackground(bgColor);
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
                if (userAnswers.get(index) != -1 && userAnswers.get(index) == answers.get(i).getAwID()) {
                    answerButtons[i].setSelected(true);
                }
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
            finishQuiz();
        }
    }

    private void saveUserAnswer() {
        JRadioButton selectedButton = null;
        for (JRadioButton btn : answerButtons) {
            if (btn.isSelected()) {
                selectedButton = btn;
                break;
            }
        }
        if (selectedButton != null) {
            int selectedAnswerID = Integer.parseInt(selectedButton.getActionCommand());
            userAnswers.set(currentIndex, selectedAnswerID);
        } else {
            userAnswers.set(currentIndex, -1);
        }
    }

    private void finishQuiz() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }

        int correctCount = 0;
        for (int i = 0; i < questions.size(); i++) {
            int userAnswerID = userAnswers.get(i);
            int correctAnswerID = quizBUS.getCorrectAnswerByQuestionID(questions.get(i).getQuestionID());
            if (userAnswerID != -1 && userAnswerID == correctAnswerID) {
                correctCount++;
            }
        }

        double score = ((double) correctCount / questions.size()) * 10.0;
        LocalDate today = LocalDate.now();
        boolean success = quizBUS.saveQuizResult(currentUser.getUserID(), testCode, questions, userAnswers, score,
                today);

        int timeTakenSeconds = initialTime - remainingTime;
        String timeTaken = String.format("%d:%02d", timeTakenSeconds / 60, timeTakenSeconds % 60);

        if (contentPanel != null) {
            ResultForm resultForm = new ResultForm(contentPanel, success, score, correctCount, totalQuestions,
                    currentUser, testCode, initialTime, remainingTime, timeTaken);
            resultForm.setVisible(true);

            // Quay lại FormSelectionTest sau khi đóng ResultForm
            resultForm.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    backToSelection();
                }
            });
        }
    }

    private void submitQuiz() {
        saveUserAnswer();
        finishQuiz();
    }

    private void backToSelection() {
        if (contentPanel != null) {
            contentPanel.removeAll();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(new FormSelectionTest(contentPanel), BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
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

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("Quiz UI Test");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            JPanel contentPanel = new JPanel();
            frame.setContentPane(contentPanel);
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(new QuizUI("TEST001", contentPanel), BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}