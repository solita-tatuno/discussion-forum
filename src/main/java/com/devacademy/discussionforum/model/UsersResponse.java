package com.devacademy.discussionforum.model;

public class UsersResponse {

    private final Integer id;

    private final String username;

    private final Boolean isAdmin;

    public UsersResponse(Integer id, String username, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }
}

