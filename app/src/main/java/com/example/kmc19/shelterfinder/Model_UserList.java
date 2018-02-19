package com.example.kmc19.shelterfinder;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by kmc19 on 2/12/2018.
 */

public class Model_UserList {
    private static ArrayList<Model_User> listUsers = new ArrayList<>();
    public Model_UserList() {
        addUser(new Model_User());
    }

    public boolean addUser(Model_User user) {
        if (!findDupe(user)) {
            listUsers.add(user);
            return true;
        }
        return false;
    }

    public Model_User findUser(Model_User user) {
        for (Model_User person: listUsers) {
            if (person.equals(user)) {
                return person;
            }
        }
        throw new java.util.NoSuchElementException("Could not find user within database.");
    }

    public boolean loginVerify(Model_User user) {
        try {
            findUser(user);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean findDupe(Model_User user) {
        for (Model_User person: listUsers) {
            if (person.getEmail().equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean findDupeUsername(String username) {
        for (Model_User user : listUsers) {
            if (user.getEmail().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
