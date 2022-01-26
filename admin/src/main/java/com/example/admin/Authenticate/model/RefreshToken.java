package com.example.admin.authenticate.model;

import java.io.Serializable;
import java.time.Instant;

import lombok.Data;

@Data
public class RefreshToken implements Serializable{

    private static final long serialVersionUID = -8091879092341046844L;

    private String token;
    // private UserDevice userDevice;
    private Instant expiryDate; 
}
