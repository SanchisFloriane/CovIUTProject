package com.example.srava.coviutproject;

/**
 * Created by sanchisf on 12/02/2016.
 */
public class Credential {

    public String userName;
    public String password;
    public String table;
    public String selectValue;
    public String value;


    public Credential() {
    }

    public Credential(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public Credential(String table, String selectValue, String value){
        this.table = table;
        this.selectValue = selectValue;
        this.value = value;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "username='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
