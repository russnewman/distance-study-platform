package com.netcracker.edu.distancestudyplatform.dto.authentication;


import lombok.Getter;
import lombok.Setter;

public class AuthenticationResponse {
    private @Getter @Setter String jwtToken;

    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public AuthenticationResponse() {
    }
}
