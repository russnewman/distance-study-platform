package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.dto.EventFormDto;
import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.dto.wrappers.EventPage;
import com.netcracker.edu.distancestudyplatform.model.Event;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface EventService {
    void saveEvent(EventFormDto eventFormDto);
    List<EventStudentDto> getAllEvents();
    List<EventStudentDto> getAllStudentEvents(Long studentId);
    List<EventStudentDto> getAllStudentSubjectEvents(Long studentId, Long subjectId);
    List<EventStudentDto> getAllActiveStudentEvents(Long studentId);
    List<EventStudentDto> getAllActiveStudentSubjectEvents(Long studentId, Long subjectId);
    EventStudentDto getEventDtoById(Long eventId);
    EventPage getEvents(Long teacherId, String sortingType, String subjectName, Integer pageNumber);
//    List<EventDto> getEvents(Long teacherId, String sortingType, String subjectName);

    void deleteEvent(Long eventId);
    EventDto getEventById(Long eventId);
    EventStudentDto getEventStudentDtoById(Long eventId);
    void editEvent(Long eventId, EventFormDto eventFormDto);
    Event getFullEventById(Long eventId);

    Boolean canDeleteEvent(Long eventId);
}
