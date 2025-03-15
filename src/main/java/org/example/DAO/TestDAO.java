package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.TestDTO;
import org.example.DTO.Test_structureDTO;

public class TestDAO {
    private Connection conn;

    public TestDAO() {
        conn = UtilsJDBC.getConnectDB();
    }

    // Phương thức kiểm tra và lấy lại kết nối nếu bị đóng
    private Connection getValidConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = UtilsJDBC.getConnectDB();
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi kiểm tra hoặc lấy kết nối: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Lấy danh sách Test theo Topic ID
     */
    public List<TestDTO> getTestsByTopicID(int tpID) {
        List<TestDTO> testList = new ArrayList<>();
        HashMap<String, Test_structureDTO> structureMap = new HashMap<>();

        // Lấy danh sách cấu trúc bài kiểm tra theo Topic ID
        String structureQuery = """
                SELECT testCode, tpID, numberEasy, numberMedium, numberDiff
                FROM test_structure
                WHERE tpID = ?
                """;
        try (PreparedStatement stmt = getValidConnection().prepareStatement(structureQuery)) {
            stmt.setInt(1, tpID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String testCode = rs.getString("testCode");
                    Test_structureDTO structure = new Test_structureDTO(
                            testCode,
                            rs.getInt("tpID"),
                            rs.getInt("numberEasy"),
                            rs.getInt("numberMedium"),
                            rs.getInt("numberDiff"));
                    structureMap.put(testCode, structure);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy cấu trúc bài kiểm tra: " + e.getMessage());
            e.printStackTrace();
            return testList;
        }

        // Lấy danh sách bài kiểm tra
        String testQuery = """
                SELECT testID, testCode, testTitle, testTime, testDate, testStatus
                FROM test
                WHERE testStatus = 1 AND testCode IN (SELECT testCode FROM test_structure WHERE tpID = ?)
                """;
        try (PreparedStatement stmt = getValidConnection().prepareStatement(testQuery)) {
            stmt.setInt(1, tpID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String testCode = rs.getString("testCode");
                    Test_structureDTO structure = structureMap.get(testCode);

                    TestDTO test = new TestDTO(
                            rs.getInt("testID"),
                            testCode,
                            rs.getString("testTitle"),
                            rs.getInt("testTime"),
                            rs.getDate("testDate"),
                            rs.getInt("testStatus"),
                            structure);
                    testList.add(test);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy danh sách bài kiểm tra: " + e.getMessage());
            e.printStackTrace();
        }
        return testList;
    }

    /**
     * Lấy Test theo Test Code
     */
    public TestDTO getTestByCode(String testCode) {
        TestDTO test = null;
        Test_structureDTO structure = null;

        // Lấy dữ liệu cấu trúc bài kiểm tra
        String structureQuery = """
                SELECT testCode, tpID, numberEasy, numberMedium, numberDiff
                FROM test_structure
                WHERE testCode = ?
                """;
        try (PreparedStatement stmt = getValidConnection().prepareStatement(structureQuery)) {
            stmt.setString(1, testCode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    structure = new Test_structureDTO(
                            rs.getString("testCode"),
                            rs.getInt("tpID"),
                            rs.getInt("numberEasy"),
                            rs.getInt("numberMedium"),
                            rs.getInt("numberDiff"));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy cấu trúc bài kiểm tra: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        // Lấy dữ liệu bài kiểm tra
        String testQuery = """
                SELECT testID, testCode, testTitle, testTime, testDate, testStatus
                FROM test
                WHERE testCode = ?
                """;
        try (PreparedStatement stmt = getValidConnection().prepareStatement(testQuery)) {
            stmt.setString(1, testCode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    test = new TestDTO(
                            rs.getInt("testID"),
                            testCode,
                            rs.getString("testTitle"),
                            rs.getInt("testTime"),
                            rs.getDate("testDate"),
                            rs.getInt("testStatus"),
                            structure);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy bài kiểm tra: " + e.getMessage());
            e.printStackTrace();
        }
        return test;
    }

    /**
     * Lấy cấu trúc bài kiểm tra theo Test Code
     */
    public Test_structureDTO getTestStructureByTestCode(String testCode) {
        String query = """
                SELECT testCode, tpID, numberEasy, numberMedium, numberDiff
                FROM test_structure
                WHERE testCode = ?
                """;
        try (PreparedStatement stmt = getValidConnection().prepareStatement(query)) {
            stmt.setString(1, testCode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Test_structureDTO(
                            rs.getString("testCode"),
                            rs.getInt("tpID"),
                            rs.getInt("numberEasy"),
                            rs.getInt("numberMedium"),
                            rs.getInt("numberDiff"));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi lấy cấu trúc bài kiểm tra: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Kiểm tra xem người dùng đã làm bài kiểm tra chưa
     */
    public boolean hasUserTakenTest(int userID, String testCode) {
        if (userID <= 0 || testCode == null || testCode.trim().isEmpty()) {
            return false;
        }

        String query = """
                SELECT COUNT(*)
                FROM results
                WHERE userID = ? AND exCode = ?
                """;
        try (PreparedStatement stmt = getValidConnection().prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setString(2, testCode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi kiểm tra bài kiểm tra của người dùng: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}