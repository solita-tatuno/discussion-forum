package com.devacademy.discussionforum.security;

import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import org.springframework.security.core.userdetails.User;


public class CustomUserDetails extends User {
    private final Integer id;
    private final boolean isAdmin;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer id, boolean isAdmin) {
        super(username, password, authorities);
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
