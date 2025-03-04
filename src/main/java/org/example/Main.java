package org.example;

import org.example.GUI.Application.Application;

public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
        // LoginForm login = new LoginForm();
        // login.setVisible(true);
        // login.pack();
        // login.setLocationRelativeTo(null);

        // // Khi đăng nhập thành công, mở Application
        // login.setLoginSuccessListener(() -> {
        // login.dispose(); // Đóng LoginForm
        // SwingUtilities.invokeLater(() -> {
        // Application.main(args);
        // });
        // });
        // });
        Application.main(args);
    }
}
