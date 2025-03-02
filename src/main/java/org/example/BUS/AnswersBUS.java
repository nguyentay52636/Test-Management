package org.example.BUS;

import org.example.DAO.AnswersDAO;
import org.example.DTO.AnswersDTO;

import java.util.List;

public class AnswersBUS {
    private AnswersDAO answersDAO;

    public AnswersBUS() {
        answersDAO = new AnswersDAO();
    }

    // Lấy danh sách tất cả đáp án
    public List<AnswersDTO> getAllAnswers() {
        return answersDAO.getAllAnswers();
    }

    // Lấy danh sách đáp án theo ID câu hỏi (qID)
    public List<AnswersDTO> getAnswersByQuestion(int qID) {
        return answersDAO.getAnswerByQuestion(qID);
    }

    // Thêm đáp án mới
    public boolean addAnswer(AnswersDTO answer) {
        return answersDAO.insertAnswer(answer);
    }

    // Cập nhật đáp án
    public boolean updateAnswer(AnswersDTO answer) {
        return answersDAO.updateAnswer(answer);
    }

    // Xóa đáp án
    public boolean deleteAnswer(int awID) {
        return answersDAO.deleteAnswer(awID);
    }
 
}
