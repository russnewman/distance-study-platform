package com.netcracker.edu.distancestudyplatform.dto;

import lombok.Data;

@Data
public class ScheduleDto {

    private Long id;
    private SubjectDto subjectDto;
    private TeacherDto teacher;
    private String classType;
    private GroupDto groupDto;
    private String dayName;
    private ClassTimeDto classTimeDto;
    private Boolean weekIsOdd;
}
