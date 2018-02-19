package com.example.kmc19.shelterfinder;

/**
 * Created by kmc19 on 2/12/2018.
 */

public class Model_User {
    private String email;
    private String name;
    private String password;
    private boolean admin;

    public Model_User() {
        this("example@example.com", "user", "password", false);
    }

    public Model_User(String email, String name, String pass, boolean admin) {
        setEmail(email);
        setName(name);
        setPassword(pass);
        setAdmin(admin);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean getAdmin() {
        return admin;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (this == other) {
            return true;
        } else if (other instanceof Model_User) {
            Model_User user = (Model_User) other;
            if (((this.email.equals(user.getEmail())) && (this.password.equals(user.getPassword())))) {
                return true;
            }
        }
        return false;
    }
}
