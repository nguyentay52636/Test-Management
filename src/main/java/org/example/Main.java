package org.example;

import org.example.GUI.LoginForm;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LoginForm login  = new LoginForm() ;
        login.setVisible(true);
        login.pack();
        login.setLocationRelativeTo(null);
        }
    }
