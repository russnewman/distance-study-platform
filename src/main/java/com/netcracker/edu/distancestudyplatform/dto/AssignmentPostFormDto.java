package com.netcracker.edu.distancestudyplatform.dto;

import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import lombok.Data;

@Data
public class AssignmentPostFormDto {
    private Long studentId;
    private String commentary;
    private DatabaseFileDto dbFileDto;
}
