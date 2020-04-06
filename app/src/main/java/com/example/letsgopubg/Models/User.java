package com.example.letsgopubg.Models;

public class User {
    private int _id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;

    public User(int _id, String email, String password, String first_name, String last_name) {
        this._id = _id;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public User() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
