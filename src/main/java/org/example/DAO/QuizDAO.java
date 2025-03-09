package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.AnswersDTO;
import org.example.DTO.QuestionDTO;
import org.json.JSONObject;

public class QuizDAO {
    private Connection conn;

    public QuizDAO() {
        {
            conn = UtilsJDBC.getConnectDB();

        }
    }

    // Lấy danh sách câu hỏi theo testCode từ ExamsDTO
    public List<QuestionDTO> getQuestionsByTestCode(String testCode) {
        List<QuestionDTO> questions = new ArrayList<>();
        
        String queryStructure = "SELECT numberEasy, numberMedium, numberDiff FROM test_structure WHERE testCode = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(queryStructure)) {
            stmt.setString(1, testCode);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                int numEasy = rs.getInt("numberEasy");
                int numMedium = rs.getInt("numberMedium");
                int numDiff = rs.getInt("numberDiff");
    
                questions.addAll(getQuestionsByLevel(testCode, "easy", numEasy));
                questions.addAll(getQuestionsByLevel(testCode, "medium", numMedium));
                questions.addAll(getQuestionsByLevel(testCode, "diff", numDiff));
                System.out.println("Dễ: " + getQuestionsByLevel(testCode, "Easy", numEasy).size());
System.out.println("Trung bình: " + getQuestionsByLevel(testCode, "Medium", numMedium).size());
System.out.println("Khó: " + getQuestionsByLevel(testCode, "diff", numDiff).size());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
    private List<QuestionDTO> getQuestionsByLevel(String testCode, String level, int limit) {
        List<QuestionDTO> questions = new ArrayList<>();
        
        if (limit <= 0) return questions; // Nếu số lượng cần lấy = 0 thì bỏ qua
        
        String sql = """
                SELECT q.* 
                FROM questions q 
                JOIN test_structure ts ON q.qTopicID = ts.tpID 
                WHERE ts.testCode = ? AND q.qLevel = ? 
                ORDER BY RAND() 
                LIMIT ?
                """;
    
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, testCode);
            stmt.setString(2, level);
            stmt.setInt(3, limit);
    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                questions.add(new QuestionDTO(
                        rs.getInt("qID"),
                        rs.getString("qContent"),
                        rs.getString("qPictures"),
                        rs.getInt("qTopicID"),
                        rs.getString("qLevel"),
                        rs.getBoolean("qStatus")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return questions;
    }
    
    

    // Lấy danh sách đáp án theo questionID
    public List<AnswersDTO> getAnswersByQuestionID(int questionID) {
        List<AnswersDTO> answers = new ArrayList<>();
        String sql = "SELECT * FROM answers WHERE qID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionID);
            ResultSet rs = stmt.executeQuery();
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

    // 🔥 Lấy ID của đáp án đúng cho một câu hỏi
    public int getCorrectAnswerByQuestionID(int questionID) {
        String sql = "SELECT awID FROM answers WHERE qID = ? AND isRight = 1 LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("awID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    public boolean saveQuizResult(int userID, String testCode, List<QuestionDTO> questions, List<Integer> userAnswers,
            int correctCount, double score, LocalDate date) {
        String sql = "INSERT INTO result (rs_num, userID, exCode, rs_anwsers, rs_mark, rs_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 🔥 Chuyển danh sách câu trả lời thành JSON {"q1":"A", "q2":"B", ...}
            JSONObject answerJson = new JSONObject();
            for (int i = 0; i < questions.size(); i++) {
                String questionKey = "q" + (i + 1);
                String userAnswer = getAnswerLetter(userAnswers.get(i)); // Chuyển ID thành A/B/C/D
                answerJson.put(questionKey, userAnswer);
            }
            String answersStr = answerJson.toString(); // Chuyển thành chuỗi JSON

            stmt.setInt(1, correctCount);
            stmt.setInt(2, userID);
            stmt.setString(3, testCode);
            stmt.setString(4, answersStr);
            stmt.setDouble(5, score);
            stmt.setDate(6, java.sql.Date.valueOf(date));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lưu kết quả: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // 🔥 Hàm chuyển đổi answerID thành A/B/C/D
    private String getAnswerLetter(int answerID) {
        switch (answerID % 4) { // Giả sử ID lần lượt theo thứ tự
            case 0:
                return "D";
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            default:
                return "?";
        }
    }

}