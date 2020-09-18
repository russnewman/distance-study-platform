package com.netcracker.edu.distancestudyplatform.dto;

import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventStudentDto {
    private Long id;
    private String teacher;
    private String subject;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String fileId;
    private List<AssignmentEventDto> assignments;
}
