package com.example.demo.security;

public enum UserPermissions {
    GUEST_READ("guest:read"),
    GUEST_WRITE("guest:write"),
    HOST_READ("host:read"),
    HOST_WRITE("host:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    PROPERTY_READ("property:read"),
    PROPERTY_WRITE("property:write"),
    RESERVATION_READ("reservation:read"),
    RESERVATION_WRITE("reservation:write");

    private final String permissions;


    UserPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
