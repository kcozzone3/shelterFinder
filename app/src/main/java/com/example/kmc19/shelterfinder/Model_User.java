package com.example.kmc19.shelterfinder;

/**
 * Created by kmc19 on 2/12/2018.
 */

public class Model_User {
    private String userid;
    private String password;
    private String email;

    public Model_User() {
        this("user", "password", "example@example.com");
    }

    public Model_User(String id, String pass, String email) {
        this.userid = id;
        this.password = pass;
        this.email = email;
    }

    public void setUserId(String id) {
        this.userid = id;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object other) {
        Model_User user = (Model_User) other;
        if (((this.userid.equals(user.userid)) && (this.password.equals(user.password)))) {
            return true;
        }
        return false;
    }
}
