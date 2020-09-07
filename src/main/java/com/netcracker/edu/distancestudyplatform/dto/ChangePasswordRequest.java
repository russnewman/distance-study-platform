package com.netcracker.edu.distancestudyplatform.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordRequest {
    private @NotBlank String oldPassword;
    private @NotBlank String newPassword;
}
