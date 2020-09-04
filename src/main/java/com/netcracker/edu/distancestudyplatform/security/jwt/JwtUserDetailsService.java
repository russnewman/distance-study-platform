package com.netcracker.edu.distancestudyplatform.security.jwt;

import com.netcracker.edu.distancestudyplatform.model.User;
import com.netcracker.edu.distancestudyplatform.service.UserService;
import com.netcracker.edu.distancestudyplatform.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        return new JwtUser(user);
    }
}
