package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.DTO.TopicsDTO;

public class TopicDAO {
    private Connection conn;

    public TopicDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tracnghiem", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách tất cả topic
    public List<TopicsDTO> getAllTopics() {
        List<TopicsDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM topics";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TopicsDTO tp = new TopicsDTO(
                        rs.getInt("tpID"),
                        rs.getString("tpTitle"),
                        rs.getInt("tpParent"),
                        rs.getBoolean("tpStatus"));
                list.add(tp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm topic mới
    public boolean insertTopic(TopicsDTO tp) {
        String sql = "INSERT INTO topics (tpID.tpTitle, tpParent, tpStatus) VALUES (?,?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(0, tp.getTopicID());
            ps.setString(1, tp.getTpTitle());
            ps.setInt(2, tp.getTpParent());
            ps.setBoolean(3, tp.getTpStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật topic
    public boolean updateTopic(TopicsDTO tp) {
        String sql = "UPDATE topics SET tpTitle = ?, tpParent = ?, tpStatus = ? WHERE tpID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tp.getTpTitle());
            ps.setInt(2, tp.getTpParent());
            ps.setBoolean(3, tp.getTpStatus());
            ps.setInt(4, tp.getTopicID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa topic
    public boolean deleteTopic(int tpID) {
        String sql = "DELETE FROM topics WHERE tpID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tpID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
