package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.TestDTO;

public class TestDAO {
    private Connection conn;

    public TestDAO() {
        conn = UtilsJDBC.getConnectDB();
    }

    public List<TestDTO> getTestsByTopicID(int tpID) {
        List<TestDTO> testList = new ArrayList<>();
        String query = """
                SELECT t.testID, t.testCode, t.testTitle, t.testTime, ts.tpID, 
                       ts.numberEasy, ts.numberMedium, ts.numberDiff, 
                       t.testLimit, t.testDate, t.testStatus
                FROM test t
                JOIN test_structure ts ON t.testCode = ts.testCode
                WHERE ts.tpID = ? AND t.testStatus = 1
                """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tpID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TestDTO test = new TestDTO(
                    rs.getInt("testID"),
                    rs.getString("testCode"),
                    rs.getString("testTitle"),
                    rs.getInt("testTime"),
                    rs.getInt("tpID"),  
                    rs.getInt("numberEasy"),
                    rs.getInt("numberMedium"),
                    rs.getInt("numberDiff"),
                    rs.getBoolean("testLimit"),
                    rs.getDate("testDate"),
                    rs.getInt("testStatus")
                );
                testList.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    public TestDTO getTestByCode(String testCode) {
        TestDTO test = null;
        String query = """
                SELECT t.testID, t.testCode, t.testTitle, t.testTime, ts.tpID, 
                       ts.numberEasy, ts.numberMedium, ts.numberDiff, 
                       t.testLimit, t.testDate, t.testStatus
                FROM test t
                JOIN test_structure ts ON t.testCode = ts.testCode
                WHERE t.testCode = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, testCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                test = new TestDTO(
                    rs.getInt("testID"),
                    rs.getString("testCode"),
                    rs.getString("testTitle"),
                    rs.getInt("testTime"),
                    rs.getInt("tpID"),  
                    rs.getInt("numberEasy"),
                    rs.getInt("numberMedium"),
                    rs.getInt("numberDiff"),
                    rs.getBoolean("testLimit"),
                    rs.getDate("testDate"),
                    rs.getInt("testStatus")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }
}
