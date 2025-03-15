package org.example.BUS;

import java.util.List;

import org.example.DAO.ExamDAO;
import org.example.DAO.UserDAO;
import org.example.DTO.ExamsDTO;

public class ExamBUS {
    private ExamDAO examDAO;
    private UserDAO userDAO;

    public ExamBUS() {
        this.examDAO = new ExamDAO();
        this.userDAO = new UserDAO();
    }

    // CRUD Operations
    public boolean createExam(ExamsDTO exam) {
        return examDAO.createExam(exam);
    }

    public boolean deleteExam(String exCode) {
        return examDAO.deleteExam(exCode);
    }

    public boolean updateExam(ExamsDTO exam) {
        return examDAO.updateExam(exam);
    }

    public ExamsDTO getExamByExCode(String exCode) {
        return examDAO.getExamByExCode(exCode);
    }

    public List<ExamsDTO> getAllExams() {
        return examDAO.getAllExams();
    }

    public String getUserFullName(int userID) {
        return userDAO.getUserFullName(userID);
    }

    // public List getResultsByTopic(int topicID) {
    // return examDAO.getResultsByTopic(topicID);
    // }
}