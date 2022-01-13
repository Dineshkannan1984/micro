package com.example.admin.authenticate.service;

import com.example.admin.authenticate.model.JwtResponse;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticateService {
    public JwtResponse generteTokens(UserDetails userDetails);
    public JwtResponse generteTokensByRefreshToken(JwtResponse refreshRequest) throws Exception;
}
