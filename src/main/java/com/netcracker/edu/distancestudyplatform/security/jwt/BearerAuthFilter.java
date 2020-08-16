package com.netcracker.edu.distancestudyplatform.security.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BearerAuthFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public BearerAuthFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = jwtTokenProvider.resolveToken(httpServletRequest);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth =  new UsernamePasswordAuthenticationToken(jwtTokenProvider.getUsername(token), "", jwtTokenProvider.getAuthorities(token));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (JwtAuthenticationException e) {
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value());
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
