package org.example.DTO;

public class QuestionDTO {
    private int questionID;
    private String qContent;
    private int qTopicID;
    private String qLevel;
    private Boolean qStatus;
    private String qPicture;

    public QuestionDTO() {
    }

    public QuestionDTO(int questionID, String qContent, String qPicture, int qTopicID, String qLevel, Boolean qStatus) {
        this.questionID = questionID;
        this.qContent = qContent;
        this.qPicture = qPicture;
        this.qTopicID = qTopicID;
        this.qLevel = qLevel;
        this.qStatus = qStatus;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQContent() {
        return qContent;
    }

    public void setQContent(String qContent) {
        this.qContent = qContent;
    }

    public int getQTopicID() {
        return qTopicID;
    }

    public void setQTopicID(int qTopicID) {
        this.qTopicID = qTopicID;
    }

    public String getQLevel() {
        return qLevel;
    }

    public void setQLevel(String qLevel) {
        this.qLevel = qLevel;
    }

    public Boolean getQStatus() {
        return qStatus;
    }

    public void setQStatus(Boolean qStatus) {
        this.qStatus = qStatus;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "questionID='" + questionID + '\'' +
                ", qContent='" + qContent + '\'' +
                ", qTopicID=" + qTopicID +
                ", qLevel='" + qLevel + '\'' +
                ", qStatus=" + qStatus +
                '}';
    }

    public void setQPicture(String qPicture) {
        this.qPicture = qPicture;
    }

    public String getQPicture() {
        return qPicture;
    }
}
