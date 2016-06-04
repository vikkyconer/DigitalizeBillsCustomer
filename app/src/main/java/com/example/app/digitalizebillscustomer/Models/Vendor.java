package com.example.app.digitalizebillscustomer.Models;

/**
 * Created by vikkycorner on 04/06/16.
 */

public class Vendor {
    private String userName;
    private String password;
    private Boolean status;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
