package com.example.finalproject;

import java.util.UUID;

public class user {

    private UUID user;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;

    public user(){
        user = UUID.randomUUID();
    }

    public user(UUID id, String n, String e, String p, Boolean a) {
        this.user = id;
        this.name = n;
        this.email = e;
        this.password = p;
        this.isAdmin = a;

    }

    public user(UUID id){
        user = id;
    }

    public UUID getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
