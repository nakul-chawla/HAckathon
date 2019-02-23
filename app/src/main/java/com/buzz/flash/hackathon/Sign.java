package com.buzz.flash.hackathon;


/**
 * Created by arshiyaahuja on 06/04/18.
 */

public class Sign {

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    int login;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String email;
    String password;
}

