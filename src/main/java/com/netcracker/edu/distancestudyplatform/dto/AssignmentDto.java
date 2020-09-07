package com.netcracker.edu.distancestudyplatform.dto;

import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import lombok.Data;

@Data
public class AssignmentDto {
    private Long id;
    private Integer grade;
    private DatabaseFile dbFile;
    private String commentary;
}
