package org.example.BUS;

import java.util.ArrayList;

import org.example.DAO.UserDAO;
import org.example.DTO.UsersDTO;

public class UserBUS {
    private ArrayList<UsersDTO> listAccount = new ArrayList<>();
    UserDAO userDAO = new UserDAO();

    public UserBUS() {

    }

}
