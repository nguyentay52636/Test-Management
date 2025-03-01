package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.ExamsDTO;

public class ExamDAO {
    public ExamDAO() {
    }

    public boolean createExam(ExamsDTO exam) {
        try {
            Connection conn = UtilsJDBC.getConnectDB();
            String query = "INSERT INTO exams (testCode, exOrder, exCode, ex_quesIDs) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, exam.getTestCode());
            stmt.setString(2, exam.getExOrder());
            stmt.setString(3, exam.getExCode());
            stmt.setString(4, exam.getExQuestionIDs());
            boolean result = stmt.executeUpdate() > 0;
            UtilsJDBC.closeConnection();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteExam(int examId) {
        try {
            String query = "DELETE FROM exams WHRE examID = ?";
            Connection conn = UtilsJDBC.getConnectDB();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, examId);
            boolean result = stmt.executeUpdate() > 0;
            UtilsJDBC.closeConnection();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateExam(ExamsDTO exam) {
        try {
            String query = "UPDATE exams SET testCode = ?, exOrder = ?, exCode = ?, ex_quesIDs = ? WHERE examID = ?";
            Connection conn = UtilsJDBC.getConnectDB();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, exam.getTestCode());
            stmt.setString(2, exam.getExOrder());
            stmt.setString(3, exam.getExCode());
            stmt.setString(4, exam.getExQuestionIDs());
            stmt.setInt(5, exam.getExamID());
            boolean result = stmt.executeUpdate() > 0;
            UtilsJDBC.closeConnection();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ExamsDTO getExamById(String examId) {
        if (examId == null || !examId.matches("\\d+")) {
            System.err.println("Invalid examId: " + examId);
            return null;
        }

        String query = "SELECT * FROM exams WHERE examID = ?";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, Integer.parseInt(examId));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String testCode = rs.getString("testCode");
                    String exOrder = rs.getString("exOrder");
                    String exCode = rs.getString("exCode");
                    String ex_quesIDs = rs.getString("ex_quesIDs");

                    return new ExamsDTO(Integer.parseInt(examId), testCode, exOrder, exCode, ex_quesIDs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ExamsDTO> getAllExams() {
        ArrayList<ExamsDTO> exams = new ArrayList<>();
        String query = "SELECT * FROM exams";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int examID = rs.getInt("examID");
                String testCode = rs.getString("testCode");
                String exOrder = rs.getString("exOrder");
                String exCode = rs.getString("exCode");
                String exQuestionIDs = rs.getString("ex_quesIDs");
                exams.add(new ExamsDTO(examID, testCode, exOrder, exCode, exQuestionIDs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }
}