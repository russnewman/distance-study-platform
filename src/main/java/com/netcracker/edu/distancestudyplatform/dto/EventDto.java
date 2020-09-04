package com.netcracker.edu.distancestudyplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;
import java.util.Optional;


@Data
@AllArgsConstructor
public class EventDto {
    private Long teacherId;
    private String subjectName;
    private String groupName;
    private String description;
    private Date startTime;
    private Date endTime;

    private DatabaseFileDto databaseFileDto;
}
