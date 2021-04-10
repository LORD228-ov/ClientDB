package com.company.lesson17client;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String login;
    private String password;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }


    public String getLogin() {
        return login;
    }



    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
