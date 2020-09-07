package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.model.Event;

import java.util.List;
import java.util.Optional;


public interface EventService {
    void saveEvent(EventDto eventDto);
    List<EventDto> getAllEvents();
    List<EventDto> getAllStudentEvents(Long studentId);
    List<EventDto> getAllStudentSubjectEvents(Long studentId, Long subjectId);
    List<EventDto> getAllActiveStudentEvents(Long studentId);
    List<EventDto> getAllActiveStudentSubjectEvents(Long studentId, Long subjectId);
    EventDto getEventById(Long eventId);
}
