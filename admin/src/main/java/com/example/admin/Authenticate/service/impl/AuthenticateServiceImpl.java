package com.example.admin.authenticate.service.impl;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.example.admin.authenticate.model.JwtResponse;
import com.example.admin.authenticate.model.RefreshToken;
import com.example.admin.authenticate.service.AuthenticateService;
import com.example.admin.authenticate.service.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateServiceImpl implements AuthenticateService{

    @Autowired
	private JwtTokenUtil jwtTokenUtil;
    
    //TODO implement concurrent map in redis cache
    ConcurrentMap<String, RefreshToken> refreshTokenMap = new ConcurrentHashMap<String, RefreshToken>();
    
    @Override
    public JwtResponse generteTokens(UserDetails userDetails){

        final String acessToken = jwtTokenUtil.generateToken(userDetails);
		final RefreshToken refreshToken = jwtTokenUtil.createRefreshToken();

        refreshTokenMap.put(refreshToken.getToken(), refreshToken);

        return new JwtResponse(acessToken,refreshToken.getToken());
    }

    @Override
    public JwtResponse generteTokensByRefreshToken(JwtResponse refreshRequest) throws Exception{

        validateRefresh(refreshRequest.getRefreshToken());
		final UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(refreshRequest.getAccessToken()),"asfvfsvfbbsdhbs",new ArrayList<>());

		final String acessToken = jwtTokenUtil.generateToken(userDetails);
		final RefreshToken refreshToken = jwtTokenUtil.createRefreshToken();
        
        refreshTokenMap.put(refreshToken.getToken(), refreshToken);
        refreshTokenMap.remove(refreshRequest.getRefreshToken());

        return new JwtResponse(acessToken,refreshToken.getToken());
    }

    private void validateRefresh(String token) throws Exception {
		RefreshToken refreshToken = refreshTokenMap.get(token);

        if(refreshToken == null)
            throw new Exception("refresh token not found", null);

		if (!jwtTokenUtil.verifyExpiration(refreshToken))
			throw new Exception("refresh token expired", null);
	}
}
