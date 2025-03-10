package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.AnswersDTO;
import org.example.DTO.QuestionDTO;

public class QuestionDAO {
    private Connection conn;

    public QuestionDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tracnghiem", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Lấy danh sách tất cả câu hỏi
    public List<QuestionDTO> getAllQuestions() {
        List<QuestionDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM questions";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                QuestionDTO q = new QuestionDTO(
                        rs.getInt("qID"),
                        rs.getString("qContent"),
                        rs.getString("qPictures"),
                        rs.getInt("qTopicID"),
                        rs.getString("qLevel"),
                        rs.getBoolean("qStatus"));
                list.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<QuestionDTO> getQuestionsByTopic(int topicID) {
        List<QuestionDTO> questions = new ArrayList<>();
        String query = "SELECT * FROM questions WHERE QTopicID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, topicID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                QuestionDTO question = new QuestionDTO(
                        rs.getInt("qID"),
                        rs.getString("qContent"),
                        rs.getString("qPictures"),
                        rs.getInt("qTopicID"),
                        rs.getString("qLevel"),
                        rs.getBoolean("qStatus"));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    // Thêm câu hỏi mới
    public boolean insertQuestion(QuestionDTO q) {
        String sql = "INSERT INTO questions (qTopicID, qContent, qLevel, qPictures, qStatus) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, q.getQTopicID());
            ps.setString(2, q.getQContent());
            ps.setString(3, q.getQLevel());
            ps.setString(4, q.getQPicture());
            ps.setBoolean(5, q.getQStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật câu hỏi
    public boolean updateQuestion(QuestionDTO q) {
        String sql = "UPDATE questions SET qTopicID = ?, qContent = ?, qLevel = ?, qPictures = ?, qStatus = ? WHERE qID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, q.getQTopicID());
            ps.setString(2, q.getQContent());
            ps.setString(3, q.getQLevel());
            ps.setString(4, q.getQPicture());
            ps.setBoolean(5, q.getQStatus());
            ps.setInt(6, q.getQuestionID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa câu hỏi
    public boolean deleteQuestion(int questionID) {
        String sql = "DELETE FROM questions WHERE qID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, questionID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm câu hỏi theo nội dung hoặc chủ đề
    public List<QuestionDTO> searchQuestions(String keyword) {
        List<QuestionDTO> list = new ArrayList<>();

        // Truy vấn chỉ tìm kiếm dựa trên qContent
        String sql = "SELECT * FROM questions WHERE qContent LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%"); // Tìm kiếm từ khóa trong nội dung
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String content = rs.getString("qContent");

                // Kiểm tra lại nếu content có chứa keyword
                if (content.toLowerCase().contains(keyword.toLowerCase())) {
                    QuestionDTO q = new QuestionDTO(
                            rs.getInt("questionID"),
                            content,
                            rs.getString("qPictures"),
                            rs.getInt("qTopicID"),
                            rs.getString("qLevel"),
                            rs.getBoolean("qStatus"));
                    list.add(q);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getNextQuestionID() {
        String sql = "SELECT MAX(qID) FROM questions";
        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) + 1; // Lấy ID lớn nhất và tăng lên 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // Trả về 1 nếu bảng rỗng
    }

    public QuestionDTO getQuestionById(int questionID) {
        String sql = "SELECT * FROM questions WHERE qID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, questionID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new QuestionDTO(
                            rs.getInt("qID"),
                            rs.getString("qContent"),
                            rs.getString("qPictures"),
                            rs.getInt("qTopicID"),
                            rs.getString("qLevel"),
                            rs.getBoolean("qStatus"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AnswersDTO> getAnswersByQuestionId(int questionID) {
        List<AnswersDTO> answers = new ArrayList<>();
        try (   PreparedStatement ps = conn.prepareStatement("SELECT * FROM answers WHERE qID = ?")) {
            ps.setInt(1, questionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                answers.add(new AnswersDTO(
                        rs.getInt("awID"),
                        rs.getInt("qID"),
                        rs.getString("awContent"),
                        rs.getString("awPictures"),
                        rs.getBoolean("isRight"),
                        rs.getBoolean("awStatus")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}