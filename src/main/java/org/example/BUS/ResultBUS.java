package org.example.BUS;

import java.util.List;

import org.example.DAO.ResultDAO;
import org.example.DTO.ResultDTO;

public class ResultBUS {
    private ResultDAO resultDAO;

    public ResultBUS() {
        this.resultDAO = new ResultDAO();
    }

    public List<ResultDTO> getResult() {
        return resultDAO.getAllResults();
    }

    public String getUserFullNameByUserID(int userID) {
        return resultDAO.getUserFullNameByUserID(userID);
    }

}