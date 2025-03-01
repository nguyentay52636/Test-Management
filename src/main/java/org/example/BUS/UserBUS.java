package org.example.BUS;

import java.util.ArrayList;

import org.example.DAO.UserDAO;
import org.example.DTO.UsersDTO;

public class UserBUS {
    private ArrayList<UsersDTO> listAccount = new ArrayList<>();
    private UserDAO userDAO = new UserDAO();

    public UserBUS() {
        listAccount = userDAO.getAllUsers();
    }

    public boolean login(String userEmail, String password) {
        return userDAO.login(userEmail, password);

    }

    public boolean register(UsersDTO user) {
        return userDAO.signUp(user);
    }

    public Boolean insertUser(UsersDTO user) {
        if (userDAO.addUser(user)) {
            listAccount.add(user);
            return true;
        }
        return false;
    }

    // public boolean getInfoUser(String userEmail, String password) {
    // return userDAO.getInfoUser(userEmail, password);
    // }
    public boolean deleteUser(int userID) {
        if (userDAO.deleteUser(userID)) {
            listAccount.removeIf(user -> user.getUserID() == userID);
            return true;
        }
        return false;
    }

    public boolean updateUser(UsersDTO user) {
        if (userDAO.updateUser(user)) {
            for (int i = 0; i < listAccount.size(); i++) {
                if (listAccount.get(i).getUserID() == user.getUserID()) {
                    listAccount.set(i, user);
                    return true;
                }
            }
        }
        return false;
    }
}
