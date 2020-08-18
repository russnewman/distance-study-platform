package com.netcracker.edu.distancestudyplatform.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;

public interface SecurityConstants {
    String HEADER_STRING = "Authorization";
    String TOKEN_PREFIX = "Bearer";
    String AUTHORITY_STRING = "authorities";
    String INVALID_TOKEN_MESSAGE = "JWT token is expired or invalid";
}
