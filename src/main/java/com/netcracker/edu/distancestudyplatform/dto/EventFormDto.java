package com.netcracker.edu.distancestudyplatform.dto;

import lombok.Data;

import java.util.Date;


/**
 * Class need for transfer event form request from ui
 * */
@Data
public class EventFormDto {

    private Long teacherId;
    private String subjectName;
    private String groupName;
    private String description;
    private Date startTime;
    private Date endTime;
    private DatabaseFileDto databaseFileDto;

}
