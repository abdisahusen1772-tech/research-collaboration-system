package com.sit.rct.model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String studentId;
    private String name;
    private String email;
    private String passwordHash;

    public User(String id, String studentId, String name, String email, String passwordHash) {
        setId(id);
        setStudentId(studentId);
        setName(name);
        setEmail(email);
        setPasswordHash(passwordHash);
    }

    public abstract String getRoleName();
    public abstract boolean canCreateProject();
    public abstract boolean canArchiveProject();
    public abstract boolean canApproveExpense();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty.");
        }
        this.id = id.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        this.studentId = studentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email == null) ? "" : email.trim();
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return name + " (" + getRoleName() + " - " + studentId + ")";
    }
}