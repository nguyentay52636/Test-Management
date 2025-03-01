package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.example.ConnectDB.UtilsJDBC;

public class DatabaseDAO {
    public Map<String, Integer> getAllAutoIncrements() {
        Map<String, Integer> autoIncrements = new HashMap<>();
        String sql = "SELECT TABLE_NAME, AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'tracnghiem'";

        try (Connection con = UtilsJDBC.getConnectDB();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                int autoIncrement = rs.getInt("AUTO_INCREMENT");
                autoIncrements.put(tableName, autoIncrement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autoIncrements;
    }
}
