package com.example.admin.authenticate.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
    
	private final String accessToken;
    private final String refreshToken;
    private String tokenType = "Bearer";
}
