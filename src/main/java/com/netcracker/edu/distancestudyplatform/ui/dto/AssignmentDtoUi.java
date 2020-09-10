package com.netcracker.edu.distancestudyplatform.ui.dto;

import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDtoUi {
    private Integer grade;
    private EventStudentDto event;
    private Student student;
    private DatabaseFileDtoUi dbFile;
    private String commentary;
}
