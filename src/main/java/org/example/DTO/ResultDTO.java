package org.example.DTO;

import java.util.Date;

public class ResultDTO {
    private int rsNum;
    private int userID;
    private String exCode;
    private String rsAnswers;
    private float rsMark;

    public int getRsNum() {
        return rsNum;
    }

    public void setRsNum(int rsNum) {
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

    private Date dateTime;

    public ResultDTO(int rsNum, int userID, String exCode, String rsAnswers, float rsMark, Date dateTime) {
        this.rsNum = rsNum;
        this.userID = userID;
        this.exCode = exCode;
        this.rsAnswers = rsAnswers;
        this.rsMark = rsMark;
        this.dateTime = dateTime;
    }

    public ResultDTO(int rsNum, int userID, String exCode, float rsMark, Date dateTime) {
        this.rsNum = rsNum;
        this.userID = userID;
        this.exCode = exCode;
        this.rsMark = rsMark;
        this.dateTime = dateTime;
    }

}