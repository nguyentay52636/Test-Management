package org.example.BUS;

import java.time.LocalDate;
import java.util.List;

import org.example.DAO.QuizDAO;
import org.example.DTO.AnswersDTO;
import org.example.DTO.QuestionDTO;

public class QuizBUS {
    private QuizDAO quizDAO;

    public QuizBUS() {
        quizDAO = new QuizDAO();
    }

    // Lấy danh sách câu hỏi theo testCode
    public List<QuestionDTO> getQuestionsByTestCode(String testCode) {
        return quizDAO.getQuestionsByTestCode(testCode);
    }

    // Lấy danh sách đáp án theo questionID
    public List<AnswersDTO> getAnswersByQuestionID(int questionID) {
        return quizDAO.getAnswersByQuestionID(questionID);
    }

    // Lấy ID của đáp án đúng
    public int getCorrectAnswerByQuestionID(int questionID) {
        return quizDAO.getCorrectAnswerByQuestionID(questionID);
    }

    // Lưu kết quả bài kiểm tra
    public boolean saveQuizResult(int userID, String testCode, List<QuestionDTO> questions, List<Integer> userAnswers, int correctCount, double score, LocalDate date) {
        return quizDAO.saveQuizResult(userID, testCode, questions, userAnswers, correctCount, score, date);
    }
}
