package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.authentication.AuthenticationRequest;
import com.netcracker.edu.distancestudyplatform.dto.authentication.AuthenticationResponse;
import com.netcracker.edu.distancestudyplatform.exception.UserNotFoundException;
import com.netcracker.edu.distancestudyplatform.security.jwt.JwtTokenProvider;
import com.netcracker.edu.distancestudyplatform.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String email = request.getEmail();
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, request.getPassword());
        try {
            authentication = authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            String errMessage = "Authentication failed. Incorrect password for email: " + email;
            log.trace(errMessage);
            throw e;
        } catch (UserNotFoundException e) {
            String errMessage = "Authentication failed. Email: " + email + " is not found";
            log.trace(errMessage, e);
            throw e;
        } catch (Exception e) {
            log.error("An unexpected exception has occurred while authenticating user " + email, e);
            throw e;
        }
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        final String jwt = jwtTokenProvider.createToken(userDetails);
        log.trace("A token has been created " + jwt + " for user with email: " + userDetails.getUsername());
        return new AuthenticationResponse(jwt);
    }
}
