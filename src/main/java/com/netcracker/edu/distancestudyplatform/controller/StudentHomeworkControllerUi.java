package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.dto.assignment.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.ui.service.AssignmentUiService;
import com.netcracker.edu.distancestudyplatform.ui.service.DatabaseFileUiService;
import com.netcracker.edu.distancestudyplatform.ui.service.EventUiService;
import com.netcracker.edu.distancestudyplatform.ui.service.StudentUiService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class StudentHomeworkControllerUi {

    private final StudentUiService studentUiService;
    private final EventUiService eventUiService;
    private final AssignmentUiService assignmentUiService;
    private final DatabaseFileUiService dbFileUiService;

    public StudentHomeworkControllerUi(StudentUiService studentUiService, EventUiService eventUiService, AssignmentUiService assignmentUiService, DatabaseFileUiService dbFileUiService) {
        this.studentUiService = studentUiService;
        this.eventUiService = eventUiService;
        this.assignmentUiService = assignmentUiService;
        this.dbFileUiService = dbFileUiService;
    }

    @GetMapping("/{userEmail}/events")
    public String getAllEvents(
            @PathVariable String userEmail,
            @RequestParam(required = false) Optional<Long> subjectId,
            Model model){
        subjectId.ifPresent(subjId ->
            {
                model.addAttribute("allSubjectEvents",
                        eventUiService.getAllStudentSubjectEvents(
                                studentUiService.getStudentByEmail(userEmail).getId(), subjId)
                );
                model.addAttribute(
                        "activeSubjectEvents",
                        eventUiService.getActiveStudentSubjectEvents(
                                studentUiService.getStudentByEmail(userEmail).getId(), subjId)
                );
            }
        );
        model.addAttribute("allEvents",
                eventUiService.getAllStudentEvents(
                        studentUiService.getStudentByEmail(userEmail).getId()
                )
        );
        model.addAttribute("activeEvents",
                eventUiService.getActiveStudentEvents(
                        studentUiService.getStudentByEmail(userEmail).getId()
                )
        );
        return "student_homework";
    }

    @GetMapping("/{userEmail}/events/{eventId}/assignments")
    public String getEventAssignments(
            @PathVariable String userEmail,
            @PathVariable Long eventId,
            Model model
    ){
        model.addAttribute("allEventAssignments",
                assignmentUiService.getEventStudentAssignments(
                        studentUiService.getStudentByEmail(userEmail).getId(), eventId
                )
        );
        model.addAttribute(
                "allAssessedEventAssignments",
                assignmentUiService.getEventStudentAssessedAssignments(
                        studentUiService.getStudentByEmail(userEmail).getId(), eventId
                )
        );
        model.addAttribute(
                "allUnassessedEventAssignments",
                assignmentUiService.getEventStudentUnassessedAssignments(
                        studentUiService.getStudentByEmail(userEmail).getId(), eventId
                )
        );
        return "student_homework";
    }

    @PostMapping(value = "/{userEmail}/events/assignments/addAssignment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addAssignment(
            @PathVariable String userEmail,
            @RequestParam Long eventId,
            @RequestParam MultipartFile file,
            @RequestParam(required = false) Optional<String> commentary
    ) throws IOException {

//        DatabaseFileDto dbFile = new DatabaseFileDto(
//                file.getOriginalFilename(),
//                file.getContentType(),
//                file.getBytes()
//        );

        AssignmentDto assignment = new AssignmentDto();
        //assignment.setStudent(studentUiService.getStudentByEmail(userEmail));
        //assignment.setEvent(eventUiService.getEvent(eventId));
       // assignment.setDbFile(dbFile);
        assignment.setCommentary(commentary.orElseGet(String::new));

        assignmentUiService.save(assignment);
        dbFileUiService.saveDatabaseFile(file);

        return "redirect:/{userEmail}/events/{eventId}/assignments";
    }
}
