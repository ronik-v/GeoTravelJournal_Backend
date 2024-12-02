package com.geotraveljournal.app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserPassword {
    private final String password;
    private final String passwordSole;
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserPassword(String password, String passwordSole) {
        this.password = password;
        this.passwordSole = passwordSole;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String hashed() {
        String passwordWithSole = this.password + this.passwordSole;
        return this.bCryptPasswordEncoder.encode(passwordWithSole);
    }

    public boolean isValid(String hashedPassword) {
        String passwordWithSole = this.password + this.passwordSole;
        return this.bCryptPasswordEncoder.matches(passwordWithSole, hashedPassword);
    }
}
