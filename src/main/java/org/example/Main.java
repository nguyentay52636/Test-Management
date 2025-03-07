package org.example;

// import org.example.GUI.Application.Application;
// import     org.example.GUI.Components.FormAuth.LoginForm;
// import javax.swing.SwingUtilities;


// public class Main {
//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             LoginForm login = new LoginForm();
//         login.setVisible(true);
//         login.pack();
//         login.setLocationRelativeTo(null);

//         // Khi đăng nhập thành công, mở Application
//         login.setLoginSuccessListener(() -> {
//         login.dispose(); // Đóng LoginForm
//         SwingUtilities.invokeLater(() -> {
//         Application.main(args);
//         });
//         });

//         });
//         // Application.main(args);
//     }
// }


import org.example.BUS.UserBUS;
import org.example.GUI.Application.Application;
import org.example.GUI.Components.FormAuth.LoginForm;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
        //     // Hiển thị màn hình đăng nhập
        //     LoginForm login = new LoginForm();
        //     login.setVisible(true);
        //     login.pack();
        //     login.setLocationRelativeTo(null);

        //     // Khi đăng nhập thành công
        //     login.setLoginSuccessListener(() -> {
        //         login.dispose(); // Đóng LoginForm
        //         SwingUtilities.invokeLater(() -> {
        //             Application.main(args); // Mở Application
        //         });
        //     });


        //     if (UserBUS.getCurrentUserName() != null) {
        //         System.out.println(UserBUS.getCurrentUserName());
        //         login.dispose();
        //         SwingUtilities.invokeLater(() -> {
        //             Application.main(args);
        //         });
        //     }
        // });
        Application.main(args);
    }
}
