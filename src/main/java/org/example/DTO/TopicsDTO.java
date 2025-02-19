package org.example.DTO;

public class TopicsDTO {
    private int topicID;
    private String tpTitle;
    private int tpParent;
    private Boolean tpStatus;

    public TopicsDTO() {
    }

    public TopicsDTO(int topicID, String tpTitle, int tpParent, Boolean tpStatus) {
        this.topicID = topicID;
        this.tpTitle = tpTitle;
        this.tpParent = tpParent;
        this.tpStatus = tpStatus;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public String getTpTitle() {
        return tpTitle;
    }

    public void setTpTitle(String tpTitle) {
        this.tpTitle = tpTitle;
    }

    public int getTpParent() {
        return tpParent;
    }

    public void setTpParent(int tpParent) {
        this.tpParent = tpParent;
    }

    public Boolean getTpStatus() {
        return tpStatus;
    }

    public void setTpStatus(Boolean tpStatus) {
        this.tpStatus = tpStatus;
    }

    @Override
    public String toString() {
        return "TopicsDTO{" +
                "topicID=" + topicID +
                ", tpTitle='" + tpTitle + '\'' +
                ", tpParent=" + tpParent +
                ", tpStatus=" + tpStatus +
                '}';
    }
}
