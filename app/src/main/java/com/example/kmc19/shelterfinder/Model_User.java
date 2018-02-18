package com.example.kmc19.shelterfinder;

/**
 * Created by kmc19 on 2/12/2018.
 */

public class Model_User {
    private String email;
    private String password;

    public Model_User() {
        this("example@example.com", "password");
    }

    public Model_User(String email, String pass) {
        setEmail(email);
        setPassword(pass);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object other) {
        Model_User user = (Model_User) other;
        if (((this.email.equals(user.email)) && (this.password.equals(user.password)))) {
            return true;
        }
        return false;
    }
}
