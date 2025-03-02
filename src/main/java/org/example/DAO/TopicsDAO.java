package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.DTO.TopicsDTO;

public class TopicsDAO {
    private Connection conn;

    public TopicsDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tracnghiem", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TopicsDTO> getAllTopics() {
        List<TopicsDTO> topicsList = new ArrayList<>();
        String query = "SELECT * FROM topics WHERE tpStatus = 1"; // Lọc chủ đề hoạt động

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TopicsDTO topic = new TopicsDTO(
                    rs.getInt("tpID"),
                    rs.getString("tpTitle"),
                    rs.getInt("tpParent"),
                    rs.getBoolean("tpParent")
                );
                topicsList.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicsList;
    }
}
