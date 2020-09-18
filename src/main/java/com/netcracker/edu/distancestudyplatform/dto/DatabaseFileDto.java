package com.netcracker.edu.distancestudyplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseFileDto {
    private String fileName;
    private String fileType;
    private byte[] file;
}
