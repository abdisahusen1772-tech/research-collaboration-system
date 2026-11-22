package com.sit.rct.storage;

import com.sit.rct.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }
}