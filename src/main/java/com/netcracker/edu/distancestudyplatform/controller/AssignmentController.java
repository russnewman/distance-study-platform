package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.dto.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.mappers.AssignmentMapper;
import com.netcracker.edu.distancestudyplatform.model.Assignment;
import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import com.netcracker.edu.distancestudyplatform.repository.AssignmentRepository;
import com.netcracker.edu.distancestudyplatform.service.AssignmentService;
import com.netcracker.edu.distancestudyplatform.service.DatabaseFileService;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final AssignmentRepository assignmentRepository;
    private final DatabaseFileService dbFileService;
    private final StudentService studentService;
    private final EventService eventService;

    public AssignmentController(AssignmentService assignmentService, AssignmentRepository assignmentRepository, DatabaseFileService dbFileService, StudentService studentService, EventService eventService) {
        this.assignmentService = assignmentService;
        this.assignmentRepository = assignmentRepository;
        this.dbFileService = dbFileService;
        this.studentService = studentService;
        this.eventService = eventService;
    }

    @GetMapping("/studentAssignments")
    public List<AssignmentDto> getAllStudentAssignments(
            @RequestParam(name = "studentId") Long studentId)
    {
      return assignmentService.getAssignmentByStudent(studentId);
    }

    @GetMapping("/studentSubjectAssignments")
    public List<AssignmentDto> getStudentSubjectAssignments(
            @RequestParam(name = "studentId") Long studentId, @RequestParam(name = "subjectId") Long subjectId)
    {
        return assignmentService.getSubjectAssignments(studentId, subjectId);
    }

    @GetMapping("/studentAssessedAssignments")
    public List<AssignmentDto> getStudentAssessedAssignments(@RequestParam(name = "studentId") Long studentId){
        return assignmentService.getAssessedAssignments(studentId);
    }

    @GetMapping("/studentUnassessedAssignments")
    public List<AssignmentDto> getStudentUnassessedAssignments(@RequestParam(name = "studentId") Long studentId){
        return assignmentService.getUnassessedAssignments(studentId);
    }

    @GetMapping("/studentActiveAssignments")
    public List<AssignmentDto> getStudentActiveAssignments(@RequestParam(name = "studentId") Long studentId){
        return assignmentService.getActiveAssignments(studentId);
    }

    @GetMapping("/studentEventAssignments")
    public List<AssignmentDto> getEventAssignments(
            @RequestParam(name = "studentId") Long studentId,
            @RequestParam(name = "eventId") Long eventId
    ) {
        return assignmentService.getEventAssignments(studentId, eventId);
    }

    @GetMapping("/studentEventAssessedAssignments")
    public List<AssignmentDto> getEventAssesedAssignments(
            @RequestParam(name = "studentId") Long studentId,
            @RequestParam(name = "eventId") Long eventId
    ) {
        return assignmentService.getEventAssessedAssignments(studentId, eventId);
    }

    @GetMapping("/studentEventUnassessedAssignments")
    public List<AssignmentDto> getEventUnassesedAssignments(
            @RequestParam(name = "studentId") Long studentId,
            @RequestParam(name = "eventId") Long eventId
    ) {
        return assignmentService.getEventUnassessedAssignments(studentId, eventId);
    }

    @PostMapping("/addAssignment")
    public void add(@RequestBody AssignmentDto assignment){
        assignmentRepository.save(AssignmentMapper.INSTANCE.toAssignment(assignment));
    }
}
