package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.DatabaseFileDto;
import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.dto.EventFormDto;
import com.netcracker.edu.distancestudyplatform.mappers.EventMapper;
import com.netcracker.edu.distancestudyplatform.model.*;
import com.netcracker.edu.distancestudyplatform.repository.EventRepository;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.GroupService;
import com.netcracker.edu.distancestudyplatform.service.SubjectService;
import com.netcracker.edu.distancestudyplatform.service.TeacherService;
import liquibase.pro.packaged.C;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


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
    public void saveEvent(EventFormDto eventFormDto) {

        Event event = new Event();

        Teacher teacher = teacherService.findById(eventFormDto.getTeacherId());
        Subject subject = subjectService.findSubjectByName(eventFormDto.getSubjectName());
        Group group = groupService.findGroupByGroupName(eventFormDto.getGroupName());
        String description = eventFormDto.getDescription();

        LocalDateTime startLdt = LocalDateTime.ofInstant(eventFormDto.getStartTime().toInstant(),
                ZoneId.systemDefault());
        LocalDateTime endLdt = LocalDateTime.ofInstant(eventFormDto.getEndTime().toInstant(),
                ZoneId.systemDefault());


        event.setTeacher(teacher);
        event.setSubject(subject);
        event.setGroup(group);
        event.setDescription(description);
        event.setStartDate(startLdt);
        event.setEndDate(endLdt);


        DatabaseFileDto databaseFileDto = eventFormDto.getDatabaseFileDto();
            DatabaseFile databaseFile = new DatabaseFile(databaseFileDto.getFileName(),
                    databaseFileDto.getFileType(),
                    databaseFileDto.getFile());

        event.setDbFile(databaseFile);
        eventRepository.save(event);
    }


    @Override
    public List<EventDto> getEvents(Long teacherId, String sortingType, String subjectName) {
        Teacher teacher = teacherService.findById(teacherId);
        List<Event> events;


        if (subjectName.equals("all"))
            events = eventRepository.findAllByTeacher(teacher).orElseGet(ArrayList::new);

        else {
            Subject subject = subjectService.findSubjectByName(subjectName);
            events = eventRepository.findAllByTeacherAndSubject(teacher, subject).orElseGet(ArrayList::new);
        }
        if (sortingType.equals("addSort")){
            Collections.reverse(events);
        }
        else{
            events.sort(new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    return o1.getEndDate().compareTo(o2.getEndDate());
                }
            });
        }

        return events.stream()
                        .map(EventMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList());
    }


    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public EventDto getEventById(Long eventId) {
        return EventMapper.INSTANCE.toDTO(eventRepository.findById(eventId).orElseThrow());
    }

    @Override
    public void editEvent(Long eventId, EventFormDto eventDto) {
        Event event = eventRepository.findById(eventId).orElseThrow();

        Group group = groupService.findGroupByGroupName(eventDto.getGroupName());
        String description = eventDto.getDescription();

        LocalDateTime endLdt = LocalDateTime.ofInstant(eventDto.getEndTime().toInstant(),
                ZoneId.systemDefault());


        event.setGroup(group);
        event.setDescription(description);
        event.setEndDate(endLdt);


        DatabaseFileDto databaseFileDto = eventDto.getDatabaseFileDto();
        if(!databaseFileDto.getFileName().isEmpty()){
            DatabaseFile databaseFile = new DatabaseFile(databaseFileDto.getFileName(),
                    databaseFileDto.getFileType(),
                    databaseFileDto.getFile());

            event.setDbFile(databaseFile);
        }


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
    public EventStudentDto getEventDtoById(Long eventId) {
        return EventMapper.INSTANCE.toDTO(
                eventRepository.findById(eventId).orElseGet(Event::new)
        );
    }
}
