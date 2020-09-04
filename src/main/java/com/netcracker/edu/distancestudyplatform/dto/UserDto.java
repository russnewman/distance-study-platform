package com.netcracker.edu.distancestudyplatform.dto;

import com.netcracker.edu.distancestudyplatform.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private Role role;
}
