package org.example.BUS;

import java.util.List;

import org.example.DAO.QuestionDAO;
import org.example.DTO.AnswersDTO;
import org.example.DTO.QuestionDTO;

public class QuestionBUS {
    private QuestionDAO questionDAO;

    public QuestionBUS() {
        this.questionDAO = new QuestionDAO();
    }

    public List<QuestionDTO> getAllQuestions() {
        return questionDAO.getAllQuestions();
    }
    public List<QuestionDTO> getQuestionsByTopic(int topicID) {
        return questionDAO.getQuestionsByTopic(topicID);
    }

    public boolean insertQuestion(QuestionDTO question) {
        return questionDAO.insertQuestion(question);
    }

    public boolean updateQuestion(QuestionDTO question) {
        return questionDAO.updateQuestion(question);
    }

    public boolean deleteQuestion(int questionID) {
        return questionDAO.deleteQuestion(questionID);
    }

    public List<QuestionDTO> searchQuestion(String keyword) {
        return questionDAO.searchQuestions(keyword);
    }

    public QuestionDTO getQuestionByID(int questionID) {
        return questionDAO.getQuestionById(questionID);
    }

    public List<AnswersDTO> getAnswersByQuestionId(int questionID) {
        return questionDAO.getAnswersByQuestionId(questionID);
    }
}
