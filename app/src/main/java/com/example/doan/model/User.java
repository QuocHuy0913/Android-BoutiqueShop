package com.example.doan.model;

import java.io.Serializable;

public class User implements Serializable {
    long idUser;
    String userName;
    String password;
    String birthday;
    int sex;
    String avatar;
    String Role;

    public User(long idUser, String userName, String password, String birthday, int sex, String avatar, String role) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
        this.avatar = avatar;
        Role = role;
    }

    public User(){}

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
