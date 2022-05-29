package com.famly.DAO;

import com.famly.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO - remove this test user class
 */
public class Users {

    private List<User> userList;

    // Method to return the list
    // of employees
    public List<User> getUserList() {

        if (userList == null) {

            userList
                    = new ArrayList<>();


        }

        return userList;

    }
    public void setEmployeeList(List<User> userList) {
        this.userList
                = userList;
    }
}
