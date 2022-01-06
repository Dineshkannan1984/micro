package com.example.admin.Authenticate.model;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshToken implements Serializable{

    private static final long serialVersionUID = -8091879092341046844L;

    private String token;
    // private UserDevice userDevice;
    private Instant expiryDate; 
}
