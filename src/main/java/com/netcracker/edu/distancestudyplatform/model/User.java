package com.netcracker.edu.distancestudyplatform.model;

import java.util.Set;

public interface User {
    Set<Authority> getAuthorities();
    String getEmail();
    String getName();
    String getSurname();
    Long getId();
    String getPassword();
    Role getRole();
    boolean isActive();
}
