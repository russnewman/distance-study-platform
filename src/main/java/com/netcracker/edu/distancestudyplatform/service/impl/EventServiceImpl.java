package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.*;
import com.netcracker.edu.distancestudyplatform.mappers.AssignmentMapper;
import com.netcracker.edu.distancestudyplatform.dto.DatabaseFileDto;
import com.netcracker.edu.distancestudyplatform.dto.StudentDto;
import com.netcracker.edu.distancestudyplatform.dto.assignment.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.dto.event.EventDto;
import com.netcracker.edu.distancestudyplatform.dto.event.EventFormDto;
import com.netcracker.edu.distancestudyplatform.dto.event.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.mappers.AssignmentMapper;
import com.netcracker.edu.distancestudyplatform.mappers.EventMapper;
import com.netcracker.edu.distancestudyplatform.mappers.EventStudentDtoMapper;
import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import com.netcracker.edu.distancestudyplatform.model.Teacher;
import com.netcracker.edu.distancestudyplatform.repository.EventRepository;
import com.netcracker.edu.distancestudyplatform.service.*;
import org.springframework.data.domain.*;
import com.netcracker.edu.distancestudyplatform.service.AssignmentService;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.GroupService;
import com.netcracker.edu.distancestudyplatform.service.RestPageImpl;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import com.netcracker.edu.distancestudyplatform.service.SubjectService;
import com.netcracker.edu.distancestudyplatform.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final GroupService groupService;
    private final StudentService studentService;

    public EventServiceImpl(EventRepository eventRepository,
                            TeacherService teacherService,
                            SubjectService subjectService,
                            GroupService groupService,
                            StudentService studentService) {
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
        if (databaseFileDto != null){

            DatabaseFile databaseFile = new DatabaseFile();
            databaseFile.setId(databaseFileDto.getId());
            event.setDbFile(databaseFile);
        }


        eventRepository.save(event);
    }


    @Override
    public Page<EventDto> getEvents(Long teacherId, String sortingType, String subjectName, Integer pageNumber) {
        Teacher teacher = teacherService.findById(teacherId);
        RestPageImpl<Event> restPage;
        Page<Event> page;

        Pageable pageable;
        int numberElementsOnPage = 3;

        if (sortingType.equals("addSort")) {
            pageable = PageRequest.of(pageNumber, numberElementsOnPage, Sort.Direction.DESC,"id");
        }
        else {
            pageable = PageRequest.of(pageNumber, numberElementsOnPage, Sort.by("endDate").ascending());
        }
        if (subjectName.equals("all")){
            page  = eventRepository.findAllByTeacher(teacher, pageable);
        }
        else {
            Subject subject = subjectService.findSubjectByName(subjectName);
            page = eventRepository.findAllByTeacherAndSubject(teacher, subject, pageable);
        }

        Page<EventDto> eventDtoPage = page.map(EventMapper.INSTANCE::toDTO);
        for(EventDto event: eventDtoPage) {
            event.setCanDeleteEvent(canDeleteEvent(event.getId()));
            event.setStatus(getEventStatus(event.getId()));
        }

        return eventDtoPage;
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
    public Event getFullEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseGet(Event::new);
    }

    @Override
    public List<EventStudentDto> getAllEvents() {
        return EventStudentDtoMapper.INSTANCE.map(eventRepository.findAll());
    }

    @Override
    public Page<Event> getAllGroupEvents(Long groupId,  Pageable pageable) {
        return eventRepository.findByGroup_Id(groupId, pageable).orElseGet(Page::empty);
    }

    @Override
    public Page<Event> getAllGroupSubjectEvents(Long groupId, Long subjectId,  Pageable pageable) {
        return eventRepository.findBySubject_IdAndGroup_Id(subjectId,groupId, pageable).orElseGet(Page::empty);
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


    private Boolean canDeleteEvent(Long eventId) {
        Event currentEvent = eventRepository.findById(eventId).orElseThrow();
        List<AssignmentDto> assignmentDtos = currentEvent.getAssignments().stream()
                                                                          .map(AssignmentMapper.INSTANCE::toDTO)
                                                                          .collect(Collectors.toList());

        for (AssignmentDto assignment: assignmentDtos){
            if (assignment.getDbFile() != null) return false;
        }
        return true;

    }


    private Boolean getEventStatus(Long eventId) {
        Event currentEvent = eventRepository.findById(eventId).orElseThrow();

        List<AssignmentDto> assignmentDtos = currentEvent.getAssignments().stream()
                                                             .map(AssignmentMapper.INSTANCE::toDTO)
                                                             .collect(Collectors.toList());


        List<StudentDto> studentsByEvent = studentService.getStudentsByGroup(currentEvent.getGroup().getId());
        if (studentsByEvent.size() != assignmentDtos.size()) return false;
        else{
            for (AssignmentDto assignment: assignmentDtos){
                if (assignment.getGrade() == null) return false;
            }
        }

        return true;

    }




}
