package org.example.BUS;

import java.util.ArrayList;

import org.example.DAO.UserDAO;
import org.example.DTO.UsersDTO;

public class UserBUS {
    private ArrayList<UsersDTO> listAccount = new ArrayList<>();
    private UserDAO userDAO = new UserDAO();
    private static String currentUserName; 
    

    public UserBUS() {
        listAccount = userDAO.getAllUsers();
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        UserBUS.currentUserName = currentUserName;
    }

    public void getUserAll() {
        listAccount = userDAO.getAllUsers();
    }

    public ArrayList<UsersDTO> getListAccount() {
        return listAccount;
    }

    public boolean login(String userEmail, String password) {
        return userDAO.login(userEmail, password);
    }

    public boolean register(UsersDTO user) {
        return userDAO.signUp(user);
    }
    public UsersDTO getUser(String userName) {
        for (UsersDTO user : listAccount) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
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

    public String[] getHeaders() {
        return new String[] { "Tên người dùng", "Email", "Mật khẩu", "Tên dầy đủ ", "Vai trò" };
    }
}
