package com.netcracker.edu.distancestudyplatform.ui.service;
import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.ui.dto.EventDto;
import java.util.List;


public interface EventUiService {
    void saveEventDto(EventDto eventDto);
    void editEvent(Long eventId, EventDto eventDto);
    List<Event> getEvents(Long teacherId, String sortingType, String subjectName);
    void deleteEvent(Long eventId);
    Event getEventById(Long eventId);
}
