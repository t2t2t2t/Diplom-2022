package com.dto;

public class Users {
   private int id;
    private String username;
    private String password;
    private String test;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTest() {
        return test;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Users(int id, String username, String password, String test) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.test = test;
    }
}
