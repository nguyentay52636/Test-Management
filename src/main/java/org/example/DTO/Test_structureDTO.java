package org.example.DTO;

public class Test_structureDTO {
    private String testCode;
    private int tpID;
    private int numberEasy;
    private int numberMedium;
    private int numberDiff;

public Test_structureDTO() {}
    public Test_structureDTO(String testCode, int tpID, int numberEasy, int numberMedium, int numberDiff) {
        this.testCode = testCode;
        this.tpID = tpID;
        this.numberEasy = numberEasy;
        this.numberMedium = numberMedium;
        this.numberDiff = numberDiff;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
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

}
