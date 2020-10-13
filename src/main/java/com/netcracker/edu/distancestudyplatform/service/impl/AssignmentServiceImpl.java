package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.dto.AssignmentPostFormDto;
import com.netcracker.edu.distancestudyplatform.dto.StudentDto;
import com.netcracker.edu.distancestudyplatform.mappers.AssignmentMapper;
import com.netcracker.edu.distancestudyplatform.mappers.DatabaseFileMapper;
import com.netcracker.edu.distancestudyplatform.model.Assignment;
import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.model.Student;
import com.netcracker.edu.distancestudyplatform.repository.AssignmentRepository;
import com.netcracker.edu.distancestudyplatform.repository.EventRepository;
import com.netcracker.edu.distancestudyplatform.service.AssignmentService;
import com.netcracker.edu.distancestudyplatform.service.EmailService;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final StudentService studentService;
    private final EventRepository eventRepository;
    private final EmailService emailService;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, StudentService studentService, EventRepository eventRepository, EmailService emailService){
        this.assignmentRepository = assignmentRepository;
        this.studentService = studentService;
        this.eventRepository = eventRepository;
        this.emailService = emailService;
    }

    @Override
    public List<AssignmentDto> getAllAssignments() {
        return AssignmentMapper.INSTANCE.map(assignmentRepository.findAll());
    }

    @Override
    public AssignmentDto getAssignment(Long id) {
        return AssignmentMapper.INSTANCE.toDTO(assignmentRepository.findById(id).orElseGet(Assignment::new));
    }

    @Override
    public List<AssignmentDto> getAssignmentByStudent(Long studentId) {
        return AssignmentMapper.INSTANCE.map(
                assignmentRepository.findAllByStudent_Id(studentId).orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<AssignmentDto> getAssessedAssignments(Long studentId) {
        return AssignmentMapper.INSTANCE.map(
                assignmentRepository.findAllByStudent_IdAndGradeIsNotNull(studentId).orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<AssignmentDto> getUnassessedAssignments(Long studentId) {
        return AssignmentMapper.INSTANCE.map(
                assignmentRepository.findAllByStudent_IdAndGradeIsNull(studentId).orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<AssignmentDto> getActiveAssignments(Long studentId) {
        return AssignmentMapper.INSTANCE.map(
                assignmentRepository.findAllByStudent_IdAndEvent_StartDateLessThanEqualAndEvent_EndDateGreaterThanEqual(
                studentId, LocalDateTime.now(), LocalDateTime.now()
            ).orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<AssignmentDto> getSubjectAssignments(Long studentId, Long subjectId) {
        return AssignmentMapper.INSTANCE.map(
                assignmentRepository.findByStudent_IdAndEvent_Subject_Id(
                        studentId, subjectId
                ).orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<AssignmentDto> getEventAssignments(Long studentId, Long eventId) {
        return AssignmentMapper.INSTANCE.map(
                assignmentRepository.findByStudent_IdAndEvent_Id(
                        studentId, eventId
                ).orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<AssignmentDto> getEventAssessedAssignments(Long studentId, Long eventId) {
        return AssignmentMapper.INSTANCE.map(
                assignmentRepository.findByStudent_IdAndEvent_IdAndGradeIsNotNull(
                        studentId, eventId
                ).orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<AssignmentDto> getEventUnassessedAssignments(Long studentId, Long eventId) {
        return AssignmentMapper.INSTANCE.map(
                assignmentRepository.findByStudent_IdAndEvent_IdAndGradeIsNull(
                        studentId, eventId
                ).orElseGet(ArrayList::new)
        );
    }

    @Override
    public void update(AssignmentDto assignmentDto) {
        Assignment assignment = assignmentRepository.findAssignmentById(assignmentDto.getId()).orElseThrow();
        assignment.setTeacherCommentary(assignmentDto.getTeacherCommentary());
        assignment.setGrade(assignmentDto.getGrade());
        assignmentRepository.save(assignment);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String subject = assignment.getEvent().getSubject().getName();
                String startDate = assignment.getEvent().getStartDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                String teacherName = assignment.getEvent().getTeacher().getName();
                String teacheSurname = assignment.getEvent().getTeacher().getSurname();
                Student student = assignment.getStudent();
                //String studentEmail = assignment.getStudent().getEmail();
                //We must to create new account, this one is tmp!!!
                String studentEmail = "alekseenko.md@phystech.edu";
                String theme = "Your work has been rated";
                String message = "Hello, " + student.getName() + "!" + '\n'
                        + "Your work in " + subject + " started on " +startDate+ " has been rated by " + teacherName +" "+teacheSurname;

                emailService.sendSimpleMessage(studentEmail, theme, message);
            }
        });
        thread.start();
    }

    @Override
    public List<AssignmentDto> getAssignmentsByEvent(Long eventId) {
        return AssignmentMapper.INSTANCE.map(assignmentRepository.findAllByEvent_Id(eventId).orElseGet(ArrayList::new));
    }


    @Override
    public void saveAssignmentPostForm(AssignmentPostFormDto assignmentDto, Long eventId) throws IOException {
        Assignment assignment = new Assignment();

        assignment.setEvent(eventRepository.findById(eventId).orElseGet(Event::new));
        assignment.setStudent(studentService.findById(assignmentDto.getStudentId()));
        assignment.setDbFile(DatabaseFileMapper.INSTANCE.toDbFile(assignmentDto.getDbFileDto()));
        assignment.setStudentCommentary(assignmentDto.getCommentary());
        assignment.setGrade(null);
        assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> getEventAssignmentsByStudent(Long eventId, Long studentId) {
        return assignmentRepository.findByStudent_IdAndEvent_Id(studentId, eventId).orElseGet(ArrayList::new);
    }
}
