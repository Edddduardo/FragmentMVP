package com.example.practicemvp.Presents;

import com.example.practicemvp.Models.LoginUser;

public class LoginPresentador {
    public View view;
    public LoginUser login;


    public LoginPresentador(View view){
        login = new LoginUser();
        this.view = view;
    }

    public interface View{
        void loginAlert(String text);
        void accesSuccest(String token, String Username);

    }

    public void login(String token, String Username){
        login.login(token,Username, this);

    }
}
