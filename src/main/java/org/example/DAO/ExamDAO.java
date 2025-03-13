package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.ExamsDTO;
import org.example.DTO.ResultDTO;

public class ExamDAO {
    public ExamDAO() {
    }

    public boolean createExam(ExamsDTO exam) {
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO exams (testCode, exOrder, exCode, ex_quesIDs) VALUES (?, ?, ?, ?)")) {
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
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM exams WHERE exCode = ?")) {
            stmt.setString(1, exCode);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateExam(ExamsDTO exam) {
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE exams SET testCode = ?, exOrder = ?, ex_quesIDs = ? WHERE exCode = ?")) {
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
        }
        return null;
    }

    public List<ExamsDTO> getAllExams() {
        List<ExamsDTO> exams = new ArrayList<>();
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

    public List<ResultDTO> getResultsByTopic(int topicID) {
        List<ResultDTO> results = new ArrayList<>();
        String query = "SELECT r.rs_num, r.userID, r.exCode, r.rs_Mark, r.rs_date " +
                "FROM result r " +
                "JOIN exams e ON r.exCode = e.exCode " +
                "JOIN test t ON e.testCode = t.testCode " +
                "JOIN topics tp ON t.tpID = tp.tpID " +
                "WHERE tp.tpID = ?";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, topicID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int rsNum = rs.getInt("rs_num"); // Matches ResultDTO constructor
                    int userID = rs.getInt("userID");
                    String exCode = rs.getString("exCode");
                    float rsMark = rs.getFloat("rs_Mark");
                    Date dateTime = rs.getTimestamp("rs_date"); // Use Timestamp for precision
                    results.add(new ResultDTO(rsNum, userID, exCode, rsMark, dateTime));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return results;
    }
}