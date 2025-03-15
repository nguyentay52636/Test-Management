package org.example.DTO;

import java.util.Date;

public class ResultDTO {
    public int getRsNum() {
        return rsNum;
    }

    public ResultDTO(int rsNum, int userID, String exCode, float rsMark) {
        this.rsNum = rsNum;
        this.userID = userID;
        this.exCode = exCode;
        this.rsMark = rsMark;
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

    public ResultDTO(int rsNum, int userID, String exCode, float rsMark, Date ds_date) {
        this.rsNum = rsNum;
        this.userID = userID;
        this.exCode = exCode;
        this.rsMark = rsMark;
        this.ds_date = ds_date;
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

    public Date getDs_date() {
        return ds_date;
    }

    public void setDs_date(Date ds_date) {
        this.ds_date = ds_date;
    }

    private int rsNum;
    private int userID;
    private String exCode;
    private String rsAnswers;
    private float rsMark;
    private Date ds_date;
    private String userFullName;

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public ResultDTO(int rsNum, int userID, String exCode, String rsAnswers, float rsMark, Date ds_date) {
        this.rsNum = rsNum;
        this.userID = userID;
        this.exCode = exCode;
        this.rsAnswers = rsAnswers;
        this.rsMark = rsMark;
        this.ds_date = ds_date;
    }

    public ResultDTO(int rsNum, int userID, String exCode, float rsMark, String userFullName) {
        this.rsNum = rsNum;
        this.userID = userID;
        this.exCode = exCode;
        this.rsMark = rsMark;
        this.userFullName = userFullName;
    }
}