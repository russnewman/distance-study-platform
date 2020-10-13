package com.netcracker.edu.distancestudyplatform.dto;

import lombok.Data;

@Data
public class ScheduleDto {
    private Long id;
    private SubjectDto subjectDto;
    private TeacherDto teacher;
    private GroupDto groupDto;
    private String classType;
    private String dayName;
    private ClassTimeDto classTimeDto;
    private boolean weekIsOdd;
    private String lessonLink;
}
