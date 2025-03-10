package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.DTO.AnswersDTO;

public class AnswersDAO {
    private Connection conn;

    public AnswersDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tracnghiem", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách tất cả đáp án
    public List<AnswersDTO> getAllAnswers() {
        List<AnswersDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM answers";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AnswersDTO aw = new AnswersDTO(
                        rs.getInt("awID"),
                        rs.getInt("qID"),
                        rs.getString("awContent"),
                        rs.getString("awPictures"),
                        rs.getBoolean("isRight"),
                        rs.getBoolean("awStatus"));
                list.add(aw);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AnswersDTO> getAnswerByQuestion(int questionID) {
        List<AnswersDTO> answers = new ArrayList<>();
        String query = "SELECT * FROM answers WHERE qID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, questionID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AnswersDTO answer = new AnswersDTO(
                        rs.getInt("awID"),
                        rs.getInt("qID"),
                        rs.getString("awContent"),
                        rs.getString("awPictures"),
                        rs.getBoolean("isRight"),
                        rs.getBoolean("awStatus"));
                answers.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
    public void deleteAllAnswersByQuestionID(int questionID) {
    	String sql = "delete from answers where qID=?";
    	try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Thêm mới đáp án
    public boolean insertAnswer(AnswersDTO answer) {
        String sql = "INSERT INTO answers (qID, awContent, awPictures, isRight, awStatus) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, answer.getQID());
            ps.setString(2, answer.getAwContent());
            ps.setString(3, answer.getAwPicture());
            ps.setBoolean(4, answer.getIsRight());
            ps.setBoolean(5, answer.getIsStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật đáp án
    public boolean updateAnswer(AnswersDTO answer) {
        String sql = "UPDATE answers SET qID = ?, awContent = ?, awPictures = ?, isRight = ?, awStatus = ? WHERE awID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, answer.getQID());
            ps.setString(2, answer.getAwContent());
            ps.setString(3, answer.getAwPicture());
            ps.setBoolean(4, answer.getIsRight());
            ps.setBoolean(5, answer.getIsStatus());
            ps.setInt(6, answer.getAwID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa đáp án
    public boolean deleteAnswer(int awID) {
        String sql = "DELETE FROM answers WHERE awID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, awID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<AnswersDTO> getAnswersByQuestionID(int questionID) {
        List<AnswersDTO> answers = new ArrayList<>();
        String query = "SELECT * FROM answers WHERE qID = ? ";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, questionID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AnswersDTO answer = new AnswersDTO(
                        rs.getInt("awID"),
                        rs.getInt("qID"),
                        rs.getString("awContent"),
                        rs.getString("awPictures"),
                        rs.getBoolean("isRight"),
                        rs.getBoolean("awStatus"));
                answers.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}
