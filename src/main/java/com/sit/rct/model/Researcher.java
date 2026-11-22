package com.sit.rct.model;

public class Researcher extends User {
    private static final long serialVersionUID = 1L;

    public Researcher(String id, String studentId, String name, String email, String passwordHash) {
        super(id, studentId, name, email, passwordHash);
    }

    @Override
    public String getRoleName() {
        return "Researcher";
    }

    @Override
    public boolean canCreateProject() {
        return false;
    }

    @Override
    public boolean canArchiveProject() {
        return false;
    }

    @Override
    public boolean canApproveExpense() {
        return false;
    }
}