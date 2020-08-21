package com.netcracker.edu.distancestudyplatform.security;

import com.netcracker.edu.distancestudyplatform.security.jwt.InvalidTokenException;
import com.netcracker.edu.distancestudyplatform.security.jwt.JwtTokenProvider;
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

import static com.netcracker.edu.distancestudyplatform.security.jwt.SecurityConstants.INVALID_TOKEN_MESSAGE;

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
        } catch (InvalidTokenException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
