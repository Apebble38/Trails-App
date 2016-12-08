package com.example.mikey.maps.AccountSystem;

/**
 * Created by Trooper on 12/7/2016.
 */

public class Account {
    private String username;
    private String password;
    private String type;

    public Account(){
        this.username = "";
        this.password = "";
        this.type = "";
    }
    public Account(String username, String password, String type){
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }
}
