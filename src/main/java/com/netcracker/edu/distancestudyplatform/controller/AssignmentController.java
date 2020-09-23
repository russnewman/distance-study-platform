package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.dto.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.mappers.AssignmentMapper;

import com.netcracker.edu.distancestudyplatform.model.Assignment;
import com.netcracker.edu.distancestudyplatform.repository.AssignmentRepository;
import com.netcracker.edu.distancestudyplatform.repository.EventRepository;
import com.netcracker.edu.distancestudyplatform.repository.StudentRepository;
import com.netcracker.edu.distancestudyplatform.service.AssignmentService;
import com.netcracker.edu.distancestudyplatform.service.DatabaseFileService;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class AssignmentController {
    private final AssignmentService assignmentService;
    private final AssignmentRepository assignmentRepository;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;

    private final DatabaseFileService dbFileService;
    private final StudentService studentService;
    private final EventService eventService;

    public AssignmentController(AssignmentService assignmentService, AssignmentRepository assignmentRepository, EventRepository eventRepository, StudentRepository studentRepository, DatabaseFileService dbFileService, StudentService studentService, EventService eventService) {
        this.assignmentService = assignmentService;
        this.assignmentRepository = assignmentRepository;
        this.eventRepository = eventRepository;
        this.studentRepository = studentRepository;
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

    @GetMapping("/{eventId}/assignments")
    public List<AssignmentDto> getEventAssignments(
            @RequestParam(name = "studentId") Long studentId,
            @PathVariable Long eventId
    ) {
        return assignmentService.getEventAssignments(studentId, eventId);
    }

    @GetMapping("/{eventId}/assignments/assessed")
    public List<AssignmentDto> getEventAssesedAssignments(
            @RequestParam(name = "studentId") Long studentId,
            @PathVariable Long eventId
    ) {
        return assignmentService.getEventAssessedAssignments(studentId, eventId);
    }

    @GetMapping("/{eventId}/assignments/unassessed")
    public List<AssignmentDto> getEventUnassesedAssignments(
            @RequestParam(name = "studentId") Long studentId,
            @PathVariable Long eventId
    ) {
        return assignmentService.getEventUnassessedAssignments(studentId, eventId);
    }

//    @PostMapping("/addAssignment")
//    public void add(@RequestBody AssignmentDto assignment){
//        assignmentRepository.save(AssignmentMapper.INSTANCE.toAssignment(assignment));
//    }

    @PostMapping("/updateAssignment")
    public void update(@RequestBody AssignmentDto assingmentDto){
        assignmentService.update(assingmentDto);
    }



//    @GetMapping("/saveEmptyAssignment")
//    public AssignmentDto saveEmptyAssignment(@RequestParam Long eventId,
//                                    @RequestParam Long studentId){
//
//
//        Assignment assignment = new Assignment();
//        assignment.setEvent(eventRepository.findById(eventId).orElseThrow());
//        assignment.setStudent(studentRepository.findById(studentId).orElseThrow());
//
//        Assignment assignment1 = assignmentRepository.save(assignment);
//
//        return AssignmentMapper.INSTANCE.toDTO(assignment1);
//    }


    @PostMapping("/addAssignment")
    public void add(@RequestBody AssignmentDto assignment){
        assignmentRepository.save(AssignmentMapper.INSTANCE.toAssignment(assignment));
    }
//
//    @PostMapping("/updateAssignment")
//    public void update(@RequestBody AssignmentDto assingmentDto){
//        assignmentService.update(assingmentDto);
//    }


    @GetMapping("/saveEmptyAssignment")
    public AssignmentDto saveEmptyAssignment(@RequestParam Long eventId,
                                    @RequestParam Long studentId){


        Assignment assignment = new Assignment();
        assignment.setEvent(eventRepository.findById(eventId).orElseThrow());
        assignment.setStudent(studentRepository.findById(studentId).orElseThrow());

        Assignment assignment1 = assignmentRepository.save(assignment);
        return AssignmentMapper.INSTANCE.toDTO(assignment1);
    }
}
