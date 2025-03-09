package org.example.DTO;

import java.util.Date;

public class TestDTO {
    private Integer testID;
    private String testCode;
    private String title;
    private int testTime; // Đổi từ String thành int
    private int tpID;
    private int numberEasy;
    private int numberMedium;
    private int numberDiff;
    private Boolean testLimit;
    private Date date;
    private int testStatus;

    public TestDTO() {
    }

    public TestDTO(Integer testID, String testCode, String title, int testTime, int tpID, int numberEasy,
            int numberMedium, int numberDiff, Boolean testLimit, Date date, int testStatus) {
        this.testID = testID;
        this.testCode = testCode;
        this.title = title;
        this.testTime = testTime;
        this.tpID = tpID;
        this.numberEasy = numberEasy;
        this.numberMedium = numberMedium;
        this.numberDiff = numberDiff;
        this.testLimit = testLimit;
        this.date = date;
        this.testStatus = testStatus;
    }

    public Integer getTestID() {
        return testID;
    }

    public void setTestID(Integer testID) {
        this.testID = testID;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTestTime() { // Đổi kiểu trả về thành int
        return testTime;
    }

    public void setTestTime(int testTime) { // Đổi kiểu tham số thành int
        this.testTime = testTime;
    }

    public int getTpID() {
        return tpID;
    }

    public void setTpID(int tpID) {
        this.tpID = tpID;
    }

    public int getNumberEasy() {
        return numberEasy;
    }

    public void setNumberEasy(int numberEasy) {
        this.numberEasy = numberEasy;
    }

    public int getNumberMedium() {
        return numberMedium;
    }

    public void setNumberMedium(int numberMedium) {
        this.numberMedium = numberMedium;
    }

    public int getNumberDiff() {
        return numberDiff;
    }

    public void setNumberDiff(int numberDiff) {
        this.numberDiff = numberDiff;
    }

    public Boolean getTestLimit() {
        return testLimit;
    }

    public void setTestLimit(Boolean testLimit) {
        this.testLimit = testLimit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(int testStatus) {
        this.testStatus = testStatus;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "testID=" + testID +
                ", testCode='" + testCode + '\'' +
                ", title='" + title + '\'' +
                ", testTime=" + testTime + // Không còn dấu nháy vì testTime là số nguyên
                ", tpID=" + tpID +
                ", numberEasy=" + numberEasy +
                ", numberMedium=" + numberMedium +
                ", numberDiff=" + numberDiff +
                ", testLimit=" + testLimit +
                ", date=" + date +
                ", testStatus=" + testStatus +
                '}';
    }
}
