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

    private boolean checkEmaiExits(String userEmail) {
        String sql = "SELECT 1 FROM users WHERE userEmail = ? LIMIT 1";
        try {
            Connection conn = UtilsJDBC.getConnectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userEmail);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean login(String userEmail, String password) {
        if (!checkEmaiExits(userEmail)) {
            System.out.println("Email không tồn tại!");
            return false;
        }
        try {
            Connection conn = (Connection) UtilsJDBC.getConnectDB();
            String query = "SELECT * FROM users WHERE userEmail = ? AND userPassword = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userEmail);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean signUp(UsersDTO user) {
        String query = "INSERT INTO users(userName, userEmail, userPassword, userFullName, isAdmin) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserEmail());
            stmt.setString(4, user.getUserFullName());
            stmt.setBoolean(5, false); //

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UsersDTO getUserByID(int userID) {

        String query = "SELECT * FROM users WHERE userID = ?";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UsersDTO(rs.getInt("userID"), rs.getString("userName"),
                            rs.getString("userEmail"), rs.getString("userPassword"),
                            rs.getString("userFullName"), rs.getBoolean("isAdmin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteUser(int userID) {
        String query = "DELETE FROM users WHERE userID = ?";
        try (Connection conn = UtilsJDBC.getConnectDB();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(UsersDTO user) {
        String sql = "UPDATE users SET userFullName = ?, userPassword = ?, userEmail = ? WHERE userID = ?";
        try {
            Connection conn = UtilsJDBC.getConnectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUserFullName());
            stmt.setString(2, user.getUserPassword());
            stmt.setString(3, user.getUserEmail());
            stmt.setInt(4, user.getUserID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addUser(UsersDTO user) {
        String sql = "INSERT INTO users (userName, userEmail, userPassword, userFullName, isAdmin) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = UtilsJDBC.getConnectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserEmail());
            stmt.setString(3, user.getUserPassword());
            stmt.setString(4, user.getUserFullName());
            stmt.setBoolean(5, user.getIsAdmin());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
