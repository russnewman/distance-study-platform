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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

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
}
