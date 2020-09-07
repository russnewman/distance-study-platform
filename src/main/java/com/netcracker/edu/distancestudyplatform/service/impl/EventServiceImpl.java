package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.DatabaseFileDto;
import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.model.*;
import com.netcracker.edu.distancestudyplatform.repository.EventRepository;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.GroupService;
import com.netcracker.edu.distancestudyplatform.service.SubjectService;
import com.netcracker.edu.distancestudyplatform.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final GroupService groupService;

    public EventServiceImpl(EventRepository eventRepository, TeacherService teacherService, SubjectService subjectService, GroupService groupService) {
        this.eventRepository = eventRepository;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.groupService = groupService;
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


        DatabaseFileDto databaseFileDto = eventDto.getDatabaseFileDto();
            DatabaseFile databaseFile = new DatabaseFile(databaseFileDto.getFileName(),
                    databaseFileDto.getFileType(),
                    databaseFileDto.getFile());


        event.setDbFile(databaseFile);

        eventRepository.save(event);
    }


    @Override
    public List<Event> getEvents(Long teacherId, String sortingType, String subjectName) {
        Teacher teacher = teacherService.findById(teacherId);
        List<Event> events;

        if (subjectName.equals("all"))
            events = eventRepository.findAllByTeacher(teacher).orElseGet(ArrayList::new);

        else {
            Subject subject = subjectService.findSubjectByName(subjectName);
            events = eventRepository.findAllByTeacherAndSubject(teacher, subject).orElseGet(ArrayList::new);
        }
        if (sortingType.equals("addSort")){
            System.out.println(sortingType);
            Collections.reverse(events);
        }
        else{
            System.out.println("deadlineSort");
            events.sort(new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    return o1.getEndDate().compareTo(o2.getEndDate());
                }
            });
        }
        return events;
    }


    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseGet(Event::new);
    }

    @Override
    public void editEvent(Long eventId, EventDto eventDto) {
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
}
