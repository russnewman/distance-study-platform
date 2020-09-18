package com.netcracker.edu.distancestudyplatform.dto;

import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.model.Student;
import com.netcracker.edu.distancestudyplatform.ui.dto.DatabaseFileDtoUi;
import lombok.Data;

@Data
public class AssignmentDto {
    private Long id;
    private Integer grade;
    private String fileId;
    private String commentary;
}
