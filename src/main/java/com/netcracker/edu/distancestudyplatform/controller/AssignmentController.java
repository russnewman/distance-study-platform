package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.dto.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.mappers.EventMapper;
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

    @PostMapping("/{studentId}/events/{eventId}/addAssignment")
    public String addAssignment(
            @PathVariable Long studentId,
            @PathVariable Long eventId,
            @RequestParam MultipartFile file,
            @RequestParam(required = false) Optional<String> commentary
    ) throws IOException {
        DatabaseFile dbFile = new DatabaseFile(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes()
        );
        Assignment assignment = new Assignment();
        assignment.setStudent(studentService.findById(studentId));
        assignment.setEvent(EventMapper.INSTANCE.fromDTO(eventService.getEventById(eventId)));
        assignment.setDbFile(dbFile);
        assignment.setCommentary(commentary.orElseGet(String::new));
        assignmentRepository.save(assignment);
        dbFileService.storeFile(file);
        return "redirect:/studentAssignments?studentId={studentId}";
    }
}
