package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.ResultDTO;

public class ResultDAO {
    public ResultDAO() {
    }

    // }
    public List<ResultDTO> getAllResults() {
        List<ResultDTO> resultList = new ArrayList<>();
        String sql = "SELECT r.rs_num, r.userID, u.userFullName, r.exCode, r.rs_mark " +
                "FROM result r " +
                "JOIN users u ON r.userID = u.userID"; // JOIN bảng users để lấy tên

        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int rsNum = rs.getInt("rs_num");
                int userID = rs.getInt("userID");
                String userFullName = rs.getString("userFullName");
                String exCode = rs.getString("exCode");
                float rsMark = rs.getFloat("rs_mark");

                resultList.add(new ResultDTO(rsNum, userID, exCode, rsMark, userFullName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    // Lấy tên người dùng theo userID
    public String getUserFullNameByUserID(int userID) {
        String query = "SELECT userFullName FROM users WHERE userID = ?";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("userFullName");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
