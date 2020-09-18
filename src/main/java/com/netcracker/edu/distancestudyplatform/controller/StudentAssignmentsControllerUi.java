package com.netcracker.edu.distancestudyplatform.ui.controller;

import com.netcracker.edu.distancestudyplatform.model.Assignment;
import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import com.netcracker.edu.distancestudyplatform.repository.AssignmentRepository;
import com.netcracker.edu.distancestudyplatform.repository.DatabaseFileRepository;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/assignments")
public class StudentAssignmentsControllerUi {
    private final StudentService studentService;
    private final EventService eventService;
    private final AssignmentRepository assignmentRepository;
    private final DatabaseFileRepository dbFileRepository;

    public StudentAssignmentsControllerUi(StudentService studentService, EventService eventService, AssignmentRepository assignmentRepository, DatabaseFileRepository dbFileRepository) {
        this.studentService = studentService;
        this.eventService = eventService;
        this.assignmentRepository = assignmentRepository;
        this.dbFileRepository = dbFileRepository;
    }
}
