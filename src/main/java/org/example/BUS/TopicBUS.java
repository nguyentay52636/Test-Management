package org.example.BUS;

import java.util.List;

import org.example.DAO.TopicDAO;
import org.example.DTO.TopicsDTO;

public class TopicBUS {
    private TopicDAO topicDAO;

    public TopicBUS() {
        topicDAO = new TopicDAO();
    }

    // Lấy danh sách tất cả topics
    public List<TopicsDTO> getAllTopics() {
        return topicDAO.getAllTopics();
    }

    // Thêm topic mới
    public boolean addTopic(TopicsDTO topic) {
        if (topic.getTpTitle() == null || topic.getTpTitle().trim().isEmpty()) {
            System.out.println("Tên chủ đề không được để trống.");
            return false;
        }
        return topicDAO.insertTopic(topic);
    }

    // Cập nhật topic
    public boolean updateTopic(TopicsDTO topic) {
        if (topic.getTopicID() <= 0) {
            System.out.println("ID chủ đề không hợp lệ.");
            return false;
        }
        return topicDAO.updateTopic(topic);
    }

    // Xóa topic
    public boolean deleteTopic(int topicID) {
        if (topicID <= 0) {
            System.out.println("ID chủ đề không hợp lệ.");
            return false;
        }
        return topicDAO.deleteTopic(topicID);
    }
}
