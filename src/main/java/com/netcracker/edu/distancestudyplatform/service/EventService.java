package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.model.Event;

import java.util.List;


public interface EventService {
    void saveEvent(EventDto eventDto);

    List<EventStudentDto> getAllEvents();
    List<EventStudentDto> getAllStudentEvents(Long studentId);
    List<EventStudentDto> getAllStudentSubjectEvents(Long studentId, Long subjectId);
    List<EventStudentDto> getAllActiveStudentEvents(Long studentId);
    List<EventStudentDto> getAllActiveStudentSubjectEvents(Long studentId, Long subjectId);
    EventStudentDto getEventDtoById(Long eventId);
    List<Event> getEvents(Long teacherId, String sortingType, String subjectName);
    void deleteEvent(Long eventId);
    Event getEventById(Long eventId);
    void editEvent(Long eventId, EventDto eventDto);
}
