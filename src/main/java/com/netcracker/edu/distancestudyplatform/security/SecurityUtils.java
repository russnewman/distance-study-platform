package com.netcracker.edu.distancestudyplatform.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static void checkHasRights(String email) {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!userEmail.equals(email)) {
            throw new AccessDeniedException("Invalid tokek for this email");
        }
    }
}
