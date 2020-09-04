package com.netcracker.edu.distancestudyplatform.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.edu.distancestudyplatform.security.jwt.InvalidTokenException;
import com.netcracker.edu.distancestudyplatform.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Profile("security")
public class BearerAuthFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;
    private ObjectMapper mapper;

    public BearerAuthFilter(JwtTokenProvider jwtTokenProvider, ObjectMapper mapper) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = jwtTokenProvider.resolveToken(httpServletRequest);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth =  new UsernamePasswordAuthenticationToken(jwtTokenProvider.getUsername(token), "", jwtTokenProvider.getAuthorities(token));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (InvalidTokenException e) {
            handleInvalidTokenException(e, httpServletResponse);
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void handleInvalidTokenException(InvalidTokenException exception, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        mapper.writeValue(response.getOutputStream(), exception.getMessage());
        response.getOutputStream().flush();
    }

}
