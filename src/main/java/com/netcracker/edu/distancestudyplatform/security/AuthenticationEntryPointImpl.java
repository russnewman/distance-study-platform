package com.netcracker.edu.distancestudyplatform.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.netcracker.edu.distancestudyplatform.security.SecurityConstants.AUTHENTICATION_METHOD_HEADER;
import static com.netcracker.edu.distancestudyplatform.security.SecurityConstants.TOKEN_PREFIX;


@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader(AUTHENTICATION_METHOD_HEADER, TOKEN_PREFIX);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
