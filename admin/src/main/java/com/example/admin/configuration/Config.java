package com.example.admin.configuration;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;


@RefreshScope
@Data
@Component
public class Config implements Serializable{

	private static final long serialVersionUID = -8091879091924046844L;

	@Value("${spring.profiles.active:null}")
	private String activeProfile;
    
    @Value("${jwt.access.token.secret}")
	private String jwtAccessTokenSecret;

	@Value("${jwt.access.token.expiry}")
	private int jwtAccessTokenExpiry;

	@Value("${jwt.refresh.token.expiry}")
	private int jwtRfreshTokenExpiry;
}
