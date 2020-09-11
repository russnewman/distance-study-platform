package com.netcracker.edu.distancestudyplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DistanceStudyPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistanceStudyPlatformApplication.class, args);
    }

    @Bean
    //Temporal
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
