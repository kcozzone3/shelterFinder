package com.example.kmc19.shelterfinder;
import java.util.ArrayList;

/**
 * Created by kmc19 on 2/12/2018.
 */

public class Model_UserList {
    private ArrayList<Model_User> listUsers = new ArrayList<>();
    public Model_UserList() {
        listUsers.add(new Model_User());
    }

    public boolean findUser(Model_User user) {
        for (Model_User person: listUsers) {
            if (person.equals(user)) {
                return true;
            }
        }
        return false;
    }
}
