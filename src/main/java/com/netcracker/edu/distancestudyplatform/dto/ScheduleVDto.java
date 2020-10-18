package com.netcracker.edu.distancestudyplatform.dto;

import com.netcracker.edu.distancestudyplatform.model.ClassType;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleVDto {
    private Long id;
    private String dayName;
    private ClassTimeDto classTimeDto;
    private SubjectDto subject;
    private String teacher;
    private Boolean weekIsOdd;
    private String classType;
    private String lessonLink;
}
