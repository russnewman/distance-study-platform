package com.netcracker.edu.distancestudyplatform.dto;

import lombok.Data;

@Data
public class ScheduleDTO {
    private Long id;
    private SubjectDTO subjectDTO;
    private TeacherDTO teacher;
    private String classType;
    private String dayName;
    private ClassTimeDTO classTimeDTO;
    private boolean weekIsOdd;
}
