package com.example.demo.security;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.UserPermissions.*;

public enum UserRoles {
    GUEST(Sets.newHashSet(GUEST_READ, GUEST_WRITE,RESERVATION_READ, RESERVATION_WRITE, PROPERTY_READ)),
    HOST(Sets.newHashSet(HOST_READ, HOST_WRITE, PROPERTY_READ, PROPERTY_WRITE, RESERVATION_READ)),
    ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, GUEST_READ, GUEST_WRITE, HOST_WRITE, HOST_READ, PROPERTY_READ, PROPERTY_WRITE, RESERVATION_READ, RESERVATION_WRITE));

    private final Set<UserPermissions> permissions;

    UserRoles(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissions()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
