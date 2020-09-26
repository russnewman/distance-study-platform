package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.*;
import com.netcracker.edu.distancestudyplatform.mappers.EventMapper;
import com.netcracker.edu.distancestudyplatform.mappers.EventStudentDtoMapper;
import com.netcracker.edu.distancestudyplatform.model.*;
import com.netcracker.edu.distancestudyplatform.repository.EventRepository;
import com.netcracker.edu.distancestudyplatform.service.*;
import org.springframework.stereotype.Service;

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
    private final AssignmentService assignmentService;


    public EventServiceImpl(EventRepository eventRepository,
                            TeacherService teacherService,
                            SubjectService subjectService,
                            GroupService groupService,
                            AssignmentService assignmentService,
                            StudentService studentService) {
        this.eventRepository = eventRepository;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.groupService = groupService;
        this.assignmentService = assignmentService;
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
        if (databaseFileDto != null){

            DatabaseFile databaseFile = new DatabaseFile();
            databaseFile.setId(databaseFileDto.getId());
            event.setDbFile(databaseFile);
        }


        eventRepository.save(event);
    }


    @Override
    public List<EventDto> getEvents(Long teacherId, String sortingType, String subjectName) {
        Teacher teacher = teacherService.findById(teacherId);
        List<Event> events;


        if (subjectName.equals("all"))
            events = eventRepository.findAllByTeacherOrderByStartDate(teacher).orElseGet(ArrayList::new);

        else {
            Subject subject = subjectService.findSubjectByName(subjectName);
            events = eventRepository.findAllByTeacherAndSubjectOrderByStartDate(teacher, subject).orElseGet(ArrayList::new);
        }
        if (sortingType.equals("addSort")) {
            Collections.reverse(events);
        } else {
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
    public EventStudentDto getEventStudentDtoById(Long eventId) {
        return EventStudentDtoMapper.INSTANCE.toDTO(eventRepository.findById(eventId).orElseThrow());
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
        if (databaseFileDto != null){

            DatabaseFile databaseFile = new DatabaseFile();
            databaseFile.setId(databaseFileDto.getId());
            event.setDbFile(databaseFile);
        }


        eventRepository.save(event);
    }

    @Override
    public Boolean canDeleteEvent(Long eventId) {
        List<AssignmentDto> l = assignmentService.getAssignmentsByEvent(eventId);

        for (AssignmentDto assignment: l){
            if (assignment.getDbFile() != null) return false;
        }
        return true;

    }


    @Override
    public Event getFullEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseGet(Event::new);
    }

    @Override
    public List<EventStudentDto> getAllEvents() {
        return EventStudentDtoMapper.INSTANCE.map(eventRepository.findAll());
    }

    @Override
    public List<EventStudentDto> getAllStudentEvents(Long studentId) {
        return castEventForStudent(
                eventRepository.findByGroup_IdOrderByStartDate(studentService.getStudentGroup(studentId).getId())
                        .orElseGet(ArrayList::new), studentId
        );
    }

    @Override
    public List<EventStudentDto> getAllStudentSubjectEvents(Long studentId, Long subjectId) {
        return castEventForStudent(
                eventRepository.findBySubject_IdAndGroup_IdOrderByStartDate(
                        subjectId, studentService.getStudentGroup(studentId).getId()
                ).orElseGet(ArrayList::new), studentId
        );
    }

    @Override
    public List<EventStudentDto> getAllActiveStudentEvents(Long studentId) {
        return castEventForStudent(
                eventRepository.findByGroup_IdAndEndDateGreaterThanOrderByStartDate(
                        studentService.getStudentGroup(studentId).getId(), LocalDateTime.now()
                ).orElseGet(ArrayList::new), studentId
        );
    }

    @Override
    public List<EventStudentDto> getAllActiveStudentSubjectEvents(Long studentId, Long subjectId) {
        return castEventForStudent(
                eventRepository.findByGroup_IdAndSubject_IdAndEndDateGreaterThanOrderByStartDate(
                        studentService.getStudentGroup(studentId).getId(), subjectId, LocalDateTime.now()
                ).orElseGet(ArrayList::new), studentId
        );
    }

    @Override
    public EventStudentDto getEventDtoById(Long eventId) {
        return EventStudentDtoMapper.INSTANCE.toDTO(
                eventRepository.findById(eventId).orElseGet(Event::new)
        );
    }

    private List<EventStudentDto> castEventForStudent(List<Event> events, Long studentId){
        for(Event event : events){
            event.setAssignments(
                        event.getAssignments()
                                .stream()
                                .filter(
                                    x -> x.getStudent().getId().equals(studentId)
                                )
                                .collect(Collectors.toList())
            );
        }
        return EventStudentDtoMapper.INSTANCE.map(events);
    }
}
