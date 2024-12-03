package com.geotraveljournal.app.dto.auth;

import java.time.Instant;

public class UserDto {
    private Long id;
    private String email;
    private final String token;
    private Instant createdAt;

    public UserDto(Long id, String email, String token, Instant createdAt) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getToken() {
        return this.token;
    }
}
