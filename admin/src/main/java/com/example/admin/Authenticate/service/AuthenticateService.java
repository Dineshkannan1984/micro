package com.example.admin.Authenticate.service;

import com.example.admin.Authenticate.model.JwtResponse;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticateService {
    public JwtResponse generteTokens(UserDetails userDetails);
    public JwtResponse generteTokensByRefreshToken(JwtResponse refreshRequest) throws Exception;
}
