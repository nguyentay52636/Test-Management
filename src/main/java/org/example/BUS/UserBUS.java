package org.example.BUS;

import org.example.DAO.UserDAO;
import org.example.DTO.UsersDTO;

public class UserBUS {
    public UserBUS() {
        userDAO = new UserDAO();
    }

    private UserDAO userDAO;

    public boolean Login(String email, String userPassword) {
        UsersDTO userDTO = UserDAO.getUserByEmail(email);
        if (userDTO != null) {
            if (userDTO.getUserPassword().equals(userPassword)) {
                return true;
            }
            if (userDTO.getUserEmail().equals(email) && userDTO.getUserPassword().equals(userPassword)) {
                return true;
            }

        }
        return false;
    }

}
