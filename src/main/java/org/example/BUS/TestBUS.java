package org.example.BUS;

import java.util.List;

import org.example.DAO.TestDAO;
import org.example.DTO.TestDTO;
import org.example.DTO.Test_structureDTO;

public class TestBUS {
    private TestDAO testDAO;

    public TestBUS() {
        testDAO = new TestDAO();
    }

    public List<TestDTO> getTestsByTopicID(int topicID) {
        return testDAO.getTestsByTopicID(topicID);
    }

    public TestDTO getTestByCode(String testCode) {
        return testDAO.getTestByCode(testCode);
    }

    public Test_structureDTO getTestStructureByTestCode(String testCode) {
        return testDAO.getTestStructureByTestCode(testCode);
    }

    public boolean hasUserTakenTest(int userID, String testCode) {
        if (userID <= 0 || testCode == null || testCode.trim().isEmpty()) {
            return false;
        }
        try {
            return testDAO.hasUserTakenTest(userID, testCode);
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }
}