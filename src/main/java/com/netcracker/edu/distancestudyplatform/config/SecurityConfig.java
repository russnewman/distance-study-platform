package com.netcracker.edu.distancestudyplatform.config;

import com.netcracker.edu.distancestudyplatform.security.BearerAuthFilter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String AUTH_ENDPOINT = "/auth";
    private @Getter BearerAuthFilter tokenFilter;
    private @Getter UserDetailsService userDetailsService;
    private @Getter PasswordEncoder passwordEncoder;
    private @Getter AuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(BearerAuthFilter tokenFilter, @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder, AuthenticationEntryPoint authenticationEntryPoint) {
        this.tokenFilter = tokenFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers(AUTH_ENDPOINT).permitAll()
            .anyRequest().authenticated()
        .and()
            .exceptionHandling()
            .authenticationEntryPoint(getAuthenticationEntryPoint())
        .and()
            .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailsService());
    }
}