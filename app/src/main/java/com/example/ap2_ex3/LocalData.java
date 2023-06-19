package com.example.ap2_ex3;

import java.util.ArrayList;
import java.util.List;

public class LocalData {
    public static List<User> users = new ArrayList<>();

    public static User getUserByName(String username) {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
