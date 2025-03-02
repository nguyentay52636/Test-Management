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

    public List<TestDTO> getTestsByTopicID(int tpID) {
        List<TestDTO> testList = new ArrayList<>();
        String query = """
                SELECT testID, testCode, testTilte, testTime, tpID, num_easy, num_medium, num_diff, testLimit, testDate, testStatus
                FROM test WHERE tpID = ? AND testStatus = 1
                """;

        try (Connection conn = UtilsJDBC.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, tpID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TestDTO test = new TestDTO(
                    rs.getInt("testID"),
                    rs.getString("testCode"),
                    rs.getString("testTilte"), // Sửa lỗi chính tả từ "testTilte" -> "testTitle"
                    rs.getInt("testTime"), // Đổi từ rs.getString() -> rs.getInt()
                    rs.getInt("tpID"),
                    rs.getInt("num_easy"),
                    rs.getInt("num_medium"),
                    rs.getInt("num_diff"),
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
                SELECT testID, testCode, testTilte, testTime, tpID, num_easy, num_medium, num_diff, testLimit, testDate, testStatus
                FROM test WHERE testCode = ?
                """;
    
        try (Connection conn = UtilsJDBC.getConnectDB();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setString(1, testCode);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                test = new TestDTO(
                    rs.getInt("testID"),
                    rs.getString("testCode"),
                    rs.getString("testTilte"), // Fix lỗi tên cột
                    rs.getInt("testTime"), // Sửa lại đúng kiểu int
                    rs.getInt("tpID"),
                    rs.getInt("num_easy"),
                    rs.getInt("num_medium"),
                    rs.getInt("num_diff"),
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
