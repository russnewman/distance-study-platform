package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.model.Event;

import java.util.List;


public interface EventService {
    void saveEvent(EventDto eventDto);
    List<Event> getEvents(Long teacherId, String sortingType, String subjectName);
    void deleteEvent(Long eventId);
    Event getEventById(Long eventId);
    void editEvent(Long eventId, EventDto eventDto);
}
