package com.netcracker.edu.distancestudyplatform.ui.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseFileDtoUi {
    private String fileName;
    private String fileType;
    private byte[] file;
}
