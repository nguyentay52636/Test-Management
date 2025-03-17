package org.example.DTO;

import java.util.Date;

public class TestDTO {
    private Integer testID;
    private String testCode;
    private String title;
    private int testTime; // Đổi từ String thành int
    private Date date;
    private int testStatus;
    private int testLimit;
    public Test_structureDTO testStructure;

    public TestDTO() {

    }

    public TestDTO(Integer testID, String testCode, String title, int testTime, Date date, int testLimit,
            int testStatus,
            Test_structureDTO testStructure) {
        this.testID = testID;
        this.testCode = testCode;
        this.title = title;
        this.testTime = testTime;
        this.date = date;
        this.testLimit = testLimit;
        this.testStatus = testStatus;
        this.testStructure = testStructure;
    }

    public TestDTO(Integer testID, String testCode, String title, int testTime, Date date, int testStatus,
            Test_structureDTO testStructure) {
        this.testID = testID;
        this.testCode = testCode;
        this.title = title;
        this.testTime = testTime;
        this.date = date;
        this.testStatus = testStatus;
        this.testStructure = testStructure;
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

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
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

    public Test_structureDTO getTestStructure() {
        return testStructure;
    }

    public void setTestStructure(Test_structureDTO testStructure) {
        this.testStructure = testStructure;
    }

    public int getTestLimit() {
        return testLimit;
    }

    public void setTestLimit(int testLimit) {
        this.testLimit = testLimit;
    }

}