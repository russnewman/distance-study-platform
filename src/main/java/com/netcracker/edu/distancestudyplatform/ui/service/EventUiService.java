package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.ui.dto.EventDto;

import java.util.List;


public interface EventUiService {
    void saveEventDto(EventDto eventDto);
    void editEvent(Long eventId, EventDto eventDto);
    List<Event> getEvents(Long teacherId, String sortingType, String subjectName);
    void deleteEvent(Long eventId);
    Event getEventById(Long eventId);
    List<EventStudentDto> getAllStudentEvents(Long studentId);
    List<EventStudentDto> getAllStudentSubjectEvents(Long studentId, Long subjectId);
    List<EventStudentDto> getActiveStudentEvents(Long studentId);
    List<EventStudentDto> getActiveStudentSubjectEvents(Long studentId, Long subjectId);
    EventStudentDto getEvent(Long eventId);
}
