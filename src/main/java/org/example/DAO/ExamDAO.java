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
        String query = "INSERT INTO exams (testCode, exOrder, exCode, ex_quesIDs) VALUES (?, ?, ?, ?)";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, exam.getTestCode());
            stmt.setString(2, exam.getExOrder());
            stmt.setString(3, exam.getExCode());
            stmt.setString(4, exam.getExQuestionIDs());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteExam(String exCode) {
        String query = "DELETE FROM exams WHERE exCode = ?";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, exCode);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateExam(ExamsDTO exam) {
        String query = "UPDATE exams SET testCode = ?, exOrder = ?, ex_quesIDs = ? WHERE exCode = ?";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, exam.getTestCode());
            stmt.setString(2, exam.getExOrder());
            stmt.setString(3, exam.getExQuestionIDs());
            stmt.setString(4, exam.getExCode());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ExamsDTO getExamByExCode(String exCode) {
        String query = "SELECT * FROM exams WHERE exCode = ?";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, exCode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String testCode = rs.getString("testCode");
                    String exOrder = rs.getString("exOrder");
                    String exQuestionIDs = rs.getString("ex_quesIDs");
                    return new ExamsDTO(testCode, exOrder, exCode, exQuestionIDs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
                String testCode = rs.getString("testCode");
                String exOrder = rs.getString("exOrder");
                String exCode = rs.getString("exCode");
                String exQuestionIDs = rs.getString("ex_quesIDs");
                exams.add(new ExamsDTO(testCode, exOrder, exCode, exQuestionIDs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }
}