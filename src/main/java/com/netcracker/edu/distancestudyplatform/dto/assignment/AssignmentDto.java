package com.netcracker.edu.distancestudyplatform.dto.assignment;

import com.netcracker.edu.distancestudyplatform.dto.DatabaseFileDto;
import com.netcracker.edu.distancestudyplatform.dto.StudentDto;
import com.netcracker.edu.distancestudyplatform.dto.event.EventDto;
import lombok.Data;

@Data
public class AssignmentDto {
    private Long id;
    private Integer grade;
    private EventDto event;
    private StudentDto student;
    private DatabaseFileDto dbFile;
    private String teacherCommentary;
    private String studentCommentary;
}
