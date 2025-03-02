package org.example.DTO;

public class ExamsDTO {
    private String testCode;
    private String exOrder;
    private String exCode;
    private String exQuestionIDs;

    public ExamsDTO(String testCode, String exOrder, String exCode, String exQuestionIDs) {
        this.testCode = testCode;
        this.exOrder = exOrder;
        this.exCode = exCode;
        this.exQuestionIDs = exQuestionIDs;
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
