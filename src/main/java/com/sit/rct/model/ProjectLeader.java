package com.sit.rct.model;

public class ProjectLeader extends User {
    private static final long serialVersionUID = 1L;

    public ProjectLeader(String id, String studentId, String name, String email, String passwordHash) {
        super(id, studentId, name, email, passwordHash);
    }

    @Override
    public String getRoleName() {
        return "Project Leader";
    }

    @Override
    public boolean canCreateProject() {
        return true;
    }

    @Override
    public boolean canArchiveProject() {
        return true;
    }

    @Override
    public boolean canApproveExpense() {
        return true;
    }
}