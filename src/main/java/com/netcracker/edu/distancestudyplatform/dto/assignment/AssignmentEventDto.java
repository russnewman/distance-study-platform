package com.netcracker.edu.distancestudyplatform.dto.assignment;

import lombok.Data;

@Data
public class AssignmentEventDto {
    private Long id;
    private Integer grade;
    private String fileId;
    private String commentary;
}
