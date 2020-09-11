package com.netcracker.edu.distancestudyplatform.ui.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * Class need for transfer event form request from ui
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
public class EventFormDto {

    private Long teacherId;
    private String subjectName;
    private String groupName;
    private String description;
    private Date startTime;
    private Date endTime;
    private DatabaseFileDto databaseFileDto;

}
