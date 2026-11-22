package com.sit.rct.service;

import com.sit.rct.model.User;
import com.sit.rct.storage.DataStore;

public class AuthService {
    private final DataStore dataStore;
    private User currentUser;

    public AuthService(DataStore dataStore) {
        if (dataStore == null) {
            throw new IllegalArgumentException("DataStore cannot be null.");
        }
        this.dataStore = dataStore;
    }

    public boolean registerUser(User user) {
        if (user == null) {
            return false;
        }
        for (User existing : dataStore.getUsers()) {
            if (existing.getStudentId().equalsIgnoreCase(user.getStudentId())) {
                return false;
            }
        }
        dataStore.getUsers().add(user);
        return true;
    }

    public User login(String studentId, String password) {
        if (studentId == null || password == null) {
            return null;
        }
        for (User user : dataStore.getUsers()) {
            if (user.getStudentId().equalsIgnoreCase(studentId.trim())
                    && user.getPasswordHash().equals(password)) {
                this.currentUser = user;
                return user;
            }
        }
        return null;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}