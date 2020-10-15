package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.event.EventDto;
import com.netcracker.edu.distancestudyplatform.dto.event.EventFormDto;
import com.netcracker.edu.distancestudyplatform.dto.event.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface EventService {
    void saveEvent(EventFormDto eventFormDto);
    List<EventStudentDto> getAllEvents();
    Page<Event> getAllGroupEvents(Long groupId, Pageable pageable);
    Page<Event> getAllGroupSubjectEvents(Long groupId, Long subjectId, Pageable pageable);
    List<EventStudentDto> getAllActiveStudentEvents(Long studentId);
    List<EventStudentDto> getAllActiveStudentSubjectEvents(Long studentId, Long subjectId);
    EventStudentDto getEventDtoById(Long eventId);

    Page<EventDto> getEvents(Long teacherId, String sortingType, String subjectName, Integer pageNumber);

    void deleteEvent(Long eventId);
    EventDto getEventById(Long eventId);
    EventStudentDto getEventStudentDtoById(Long eventId);
    void editEvent(Long eventId, EventFormDto eventFormDto);
    Event getFullEventById(Long eventId);

}
