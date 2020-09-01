package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.model.Assignment;
import com.netcracker.edu.distancestudyplatform.repository.AssignmentRepository;
import com.netcracker.edu.distancestudyplatform.service.impl.AssignmentServiceImpl;
import com.netcracker.edu.distancestudyplatform.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
public class AssignmentController {
    private final AssignmentServiceImpl assignmentService;
    private final AssignmentRepository assignmentRepository;
    private final StudentServiceImpl studentService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public AssignmentController(AssignmentServiceImpl assignmentService, AssignmentRepository assignmentRepository, StudentServiceImpl studentService) {
        this.assignmentService = assignmentService;
        this.assignmentRepository = assignmentRepository;
        this.studentService = studentService;
    }

    @GetMapping("/studentAssignments")
    public List<Assignment> getAllStudentAssignments(
            @RequestParam(name = "studentId") Long studentId
    ){
      return assignmentService.getAssignmentByStudent(studentId);
    }

    @PostMapping("/addAssignment")
    public Assignment add(
            @RequestBody Assignment newAssignment,
            @RequestParam MultipartFile file
    ) {
        return assignmentRepository.save(newAssignment);//fixme
    }
}
