package org.example.DTO;


import java.util.Date;

public class LogsDTO {
    private int logID;
    private String logContent;
    private int logExID;
    private Date datetime;

    public LogsDTO() {
    }

    public LogsDTO(int logID, String logContent, int logExID, Date datetime) {
        this.logID = logID;
        this.logContent = logContent;
        this.logExID = logExID;
        this.datetime = datetime;
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public int getLogExID() {
        return logExID;
    }

    public void setLogExID(int logExID) {
        this.logExID = logExID;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "LogsDTO{" +
                "logID=" + logID +
                ", logContent='" + logContent + '\'' +
                ", logExID=" + logExID +
                ", datetime=" + datetime +
                '}';
    }
}
