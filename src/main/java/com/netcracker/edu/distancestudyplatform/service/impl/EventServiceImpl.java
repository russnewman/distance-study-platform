package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.ui.dto.DatabaseFileDtoUi;
import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.mappers.EventMapper;
import com.netcracker.edu.distancestudyplatform.model.*;
import com.netcracker.edu.distancestudyplatform.repository.EventRepository;
import com.netcracker.edu.distancestudyplatform.service.*;
import com.netcracker.edu.distancestudyplatform.ui.dto.EventDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final GroupService groupService;
    private final StudentService studentService;


    public EventServiceImpl(EventRepository eventRepository, TeacherService teacherService, SubjectService subjectService, GroupService groupService, StudentService studentService) {
        this.eventRepository = eventRepository;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @Override
    public void saveEvent(EventDto eventDto) {

        Event event = new Event();

        Teacher teacher = teacherService.findById(eventDto.getTeacherId());
        Subject subject = subjectService.findSubjectByName(eventDto.getSubjectName());
        Group group = groupService.findGroupByGroupName(eventDto.getGroupName());
        String description = eventDto.getDescription();

        LocalDateTime startLdt = LocalDateTime.ofInstant(eventDto.getStartTime().toInstant(),
                ZoneId.systemDefault());
        LocalDateTime endLdt = LocalDateTime.ofInstant(eventDto.getEndTime().toInstant(),
                ZoneId.systemDefault());


        event.setTeacher(teacher);
        event.setSubject(subject);
        event.setGroup(group);
        event.setDescription(description);
        event.setStartDate(startLdt);
        event.setEndDate(endLdt);


        DatabaseFileDtoUi databaseFileDto = eventDto.getDatabaseFileDto();
            DatabaseFile databaseFile = new DatabaseFile(databaseFileDto.getFileName(),
                    databaseFileDto.getFileType(),
                    databaseFileDto.getFile());


        event.setDbFile(databaseFile);

        eventRepository.save(event);
    }

    @Override
    public List<EventStudentDto> getAllEvents() {
        return EventMapper.INSTANCE.map(eventRepository.findAll());
    }

    @Override
    public List<EventStudentDto> getAllStudentEvents(Long studentId) {
        return EventMapper.INSTANCE.map(
                eventRepository.findByGroup_Id(studentService.getStudentGroup(studentId).getId())
                        .orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<EventStudentDto> getAllStudentSubjectEvents(Long studentId, Long subjectId) {
        return EventMapper.INSTANCE.map(
                eventRepository.findBySubject_IdAndGroup_Id(
                        subjectId, studentService.getStudentGroup(studentId).getId()
                ).orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<EventStudentDto> getAllActiveStudentEvents(Long studentId) {
        return EventMapper.INSTANCE.map(
                eventRepository.findByGroup_IdAndEndDateGreaterThan(
                        studentService.getStudentGroup(studentId).getId(), LocalDateTime.now()
                ).orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<EventStudentDto> getAllActiveStudentSubjectEvents(Long studentId, Long subjectId) {
        return EventMapper.INSTANCE.map(
                eventRepository.findByGroup_IdAndSubject_IdAndEndDateGreaterThan(
                        studentService.getStudentGroup(studentId).getId(), subjectId, LocalDateTime.now()
                ).orElseGet(ArrayList::new)
        );
    }

    @Override
    public EventStudentDto getEventById(Long eventId) {
        return EventMapper.INSTANCE.toDTO(
                eventRepository.findById(eventId).orElseGet(Event::new)
        );
    }
}
