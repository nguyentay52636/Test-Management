package org.example.BUS;

import java.util.List;

import org.example.DAO.TestDAO;
import org.example.DTO.TestDTO;

public class TestBUS {
    private TestDAO testDAO;

    public TestBUS() {
        testDAO = new TestDAO();
    }

    // Lấy danh sách bài thi theo topicID
    public List<TestDTO> getTestsByTopicID(int topicID) {
        return testDAO.getTestsByTopicID(topicID);
    }

    // Lấy thông tin bài thi theo testCode
    public TestDTO getTestByCode(String testCode) {
        return testDAO.getTestByCode(testCode);
    }

    // // Lấy số lượng câu hỏi của một bài kiểm tra
    // public int getTotalQuestions(TestDTO test) {
    //     return test.getNumberEasy() + test.getNumberMedium() + test.getNumberDiff();
    // }
}
