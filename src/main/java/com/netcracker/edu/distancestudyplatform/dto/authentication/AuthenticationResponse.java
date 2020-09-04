package com.netcracker.edu.distancestudyplatform.dto.authentication;


import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwtToken;

    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public AuthenticationResponse() {
    }
}
