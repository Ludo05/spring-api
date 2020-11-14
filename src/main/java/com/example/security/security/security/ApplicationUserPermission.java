package com.example.security.security.security;

public enum ApplicationUserPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read");

    private final String permissions;

    ApplicationUserPermission(String permissions) {
        this.permissions = permissions;
    }

    public String getPermission() {
        return permissions;
    }
}
