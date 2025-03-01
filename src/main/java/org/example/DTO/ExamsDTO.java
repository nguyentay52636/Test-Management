package org.example.DTO;

public class ExamsDTO {
    private int examID;
    private String testCode;
    private String exOrder;
    private String exCode;
    private String exQuestionIDs;

    public ExamsDTO(int examID, String testCode, String exOrder, String exCode, String exQuestionIDs) {
        this.examID = examID;
        this.testCode = testCode;
        this.exOrder = exOrder;
        this.exCode = exCode;
        this.exQuestionIDs = exQuestionIDs;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getExOrder() {
        return exOrder;
    }

    public void setExOrder(String exOrder) {
        this.exOrder = exOrder;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getExQuestionIDs() {
        return exQuestionIDs;
    }

    public void setExQuestionIDs(String exQuestionIDs) {
        this.exQuestionIDs = exQuestionIDs;
    }

    @Override
    public String toString() {
        return "ExamsDTO{" +
                "testCode='" + testCode + '\'' +
                ", exOrder='" + exOrder + '\'' +
                ", exCode='" + exCode + '\'' +
                ", exQuestionIDs='" + exQuestionIDs + '\'' +
                '}';
    }

}
