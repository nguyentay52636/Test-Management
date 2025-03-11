package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.ResultDTO;
import org.example.DTO.TestDTO;
import org.example.DTO.Test_structureDTO;

public class TestDAO {
    private Connection conn;

    public TestDAO() {
        conn = UtilsJDBC.getConnectDB();
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
        try (PreparedStatement stmt = conn.prepareStatement(structureQuery)) {
            stmt.setInt(1, tpID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String testCode = rs.getString("testCode");
                Test_structureDTO structure = new Test_structureDTO(
                    testCode,
                    rs.getInt("tpID"),
                    rs.getInt("numberEasy"),
                    rs.getInt("numberMedium"),
                    rs.getInt("numberDiff")
                );
                structureMap.put(testCode, structure);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return testList;
        }

        // Lấy danh sách bài kiểm tra
        String testQuery = """
                SELECT testID, testCode, testTitle, testTime, testDate, testStatus 
                FROM test 
                WHERE testStatus = 1 AND testCode IN (SELECT testCode FROM test_structure WHERE tpID = ?)
                """;
        try (PreparedStatement stmt = conn.prepareStatement(testQuery)) {
            stmt.setInt(1, tpID);
            ResultSet rs = stmt.executeQuery();
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
                    structure
                );
                testList.add(test);
            }
        } catch (SQLException e) {
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
        try (PreparedStatement stmt = conn.prepareStatement(structureQuery)) {
            stmt.setString(1, testCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                structure = new Test_structureDTO(
                    rs.getString("testCode"),
                    rs.getInt("tpID"),
                    rs.getInt("numberEasy"),
                    rs.getInt("numberMedium"),
                    rs.getInt("numberDiff")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        // Lấy dữ liệu bài kiểm tra
        String testQuery = """
                SELECT testID, testCode, testTitle, testTime, testDate, testStatus 
                FROM test 
                WHERE testCode = ?
                """;
        try (PreparedStatement stmt = conn.prepareStatement(testQuery)) {
            stmt.setString(1, testCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                test = new TestDTO(
                    rs.getInt("testID"),
                    testCode,
                    rs.getString("testTitle"),
                    rs.getInt("testTime"),
                    rs.getDate("testDate"),
                    rs.getInt("testStatus"),
                    structure
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }
    public Test_structureDTO getTestStructureByTestCode(String testCode) {
        Test_structureDTO testStructure = null;
        String query = "SELECT * FROM test_structure WHERE testCode = ?"; // Corrected table name
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, testCode);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                testStructure = new Test_structureDTO();
                testStructure.setTestCode(rs.getString("testCode"));
                testStructure.setNumberEasy(rs.getInt("numberEasy"));
                testStructure.setNumberMedium(rs.getInt("numberMedium"));
                testStructure.setNumberDiff(rs.getInt("numberDiff"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testStructure;
    }
    public boolean hasUserTakenTest(int userID, String testCode) {
        if (userID <= 0 || testCode == null || testCode.trim().isEmpty()) {
            return false;
        }

        String query = """
                SELECT COUNT(*) 
                FROM results 
                WHERE userID = ? AND exCode = ?
                """;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setString(2, testCode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
          
        }
        return false;
    }
    public ResultDTO getResultForUser(int userID, String testCode) {
        String query = """
                SELECT rsNum, userID, exCode, rsAnswers, rsMark, dateTime 
                FROM results 
                WHERE userID = ? AND exCode = ?
                """;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setString(2, testCode);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ResultDTO(
                        rs.getBoolean("rsNum"),
                        rs.getInt("userID"),
                        rs.getString("exCode"),
                        rs.getString("rsAnswers"),
                        rs.getFloat("rsMark"),
                        rs.getTimestamp("dateTime") 
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null; 
    }
}
