package org.example.DTO;

import java.util.Date;

public class ResultDTO {
    private Boolean rsNum;
    private int userID;
    private String exCode;
    private String rsAnswers;
    private float rsMark;
    private Date dateTime;

    public ResultDTO() {
    }

    public ResultDTO(Boolean rsNum, int userID, String exCode, String rsAnswers, float rsMark, Date dateTime) {
        this.rsNum = rsNum;
        this.userID = userID;
        this.exCode = exCode;
        this.rsAnswers = rsAnswers;
        this.rsMark = rsMark;
        this.dateTime = dateTime;
    }

    public Boolean getRsNum() {
        return rsNum;
    }

    public void setRsNum(Boolean rsNum) {
        this.rsNum = rsNum;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getRsAnswers() {
        return rsAnswers;
    }

    public void setRsAnswers(String rsAnswers) {
        this.rsAnswers = rsAnswers;
    }

    public float getRsMark() {
        return rsMark;
    }

    public void setRsMark(float rsMark) {
        this.rsMark = rsMark;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "rsNum=" + rsNum +
                ", userID=" + userID +
                ", exCode='" + exCode + '\'' +
                ", rsAnswers='" + rsAnswers + '\'' +
                ", rsMark=" + rsMark +
                ", dateTime=" + dateTime +
                '}';
    }
}
