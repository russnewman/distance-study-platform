package com.netcracker.edu.distancestudyplatform.dto;

import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventStudentDto {
    private Long id;
    private TeacherDto teacherDto;
    private SubjectDto subjectDto;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private DatabaseFile dbFile;
}
