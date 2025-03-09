package org.example.DTO;

public class AnswersDTO {
    private int qID;
    private int awID;
    private String awContent;
    private String awPicture;
    private Boolean isRight;
    private Boolean isStatus;

    public AnswersDTO() {
    }

    public AnswersDTO(int awID, int qID, String awContent, String awPicture, Boolean isRight, Boolean isStatus) {
        this.awID = awID;
        this.qID = qID;
        this.awContent = awContent;
        this.awPicture = awPicture;
        this.isRight = isRight;
        this.isStatus = isStatus;
    }

    public int getAwID() {
        return awID;
    }

    public void setAwID(int awID) {
        this.awID = awID;
    }

    public String getAwContent() {
        return awContent;
    }

    public void setAwContent(String awContent) {
        this.awContent = awContent;
    }

    public String getAwPicture() {
        return awPicture;
    }

    public void setAwPicture(String awPicture) {
        this.awPicture = awPicture;
    }

    public Boolean getIsRight() {
        return isRight;
    }

    public void setIsRight(Boolean isRight) {
        this.isRight = isRight;
    }

    public Boolean getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Boolean isStatus) {
        this.isStatus = isStatus;
    }

    @Override
    public String toString() {
        return "AnswersDTO{" +
                "awID=" + awID +
                ", awContent='" + awContent + '\'' +
                ", awPicture='" + awPicture + '\'' +
                ", isRight=" + isRight +
                ", isStatus=" + isStatus +
                '}';
    }

    public int getQID() {
        return qID;
    }

    public void setQID(int qID) {
        this.qID = qID;
    }
}
