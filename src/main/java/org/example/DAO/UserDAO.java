package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.UsersDTO;

public class UserDAO {
    public UserDAO() {

    }

    public ArrayList<UsersDTO> getAllUsers() {
        ArrayList<UsersDTO> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection conn = UtilsJDBC.getConnectDB();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new UsersDTO(rs.getInt("userID"), rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("userPassword"), rs.getString("userFullName"),
                        rs.getBoolean("isAdmin")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UtilsJDBC.closeConnection();
        }
        return users;
    }

    public static UsersDTO getUserByEmail(String userName) {
        String query = "SELECT * FROM users WHERE userName = ?";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsersDTO(rs.getInt("userID"), rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("userPassword"), rs.getString("userFullName"),
                        rs.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UtilsJDBC.closeConnection();
        }
        return null;
    }

    public int Login(String userName, String password) {
        int res = 0;
        try {
            Connection conn = (Connection) UtilsJDBC.getConnectDB();
            String query = "SELECT * FROM users WHERE userName = ? AND userPassword = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                res = 1;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public UsersDTO getInfoUser(String userName, String password) {
        UsersDTO user = new UsersDTO();
        try {
            Connection conn = (Connection) UtilsJDBC.getConnectDB();
            String query = "SELECT * FROM users WHERE userName = ? AND userPassword = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setUserID(rs.getInt("userID"));
                user.setUserName(rs.getString("userName"));
                user.setUserEmail(rs.getString("userEmail"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setUserFullName(rs.getString("userFullName"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
