package com.netcracker.edu.distancestudyplatform.ui.dto;


import lombok.Data;
import java.util.Date;

@Data
public class EventDto {
    private Long teacherId;
    private String subjectName;
    private String groupName;
    private String description;
    private Date startTime;
    private Date endTime;
    private DatabaseFileDto databaseFileDto;

}
