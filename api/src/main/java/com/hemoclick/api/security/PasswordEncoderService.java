package com.hemoclick.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encodePassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}