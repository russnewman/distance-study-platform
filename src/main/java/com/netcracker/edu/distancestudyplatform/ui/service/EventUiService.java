package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.ui.dto.EventDto;

import java.util.List;


public interface EventUiService {
    void saveEventDto(EventDto eventDto);
    List<EventStudentDto> getAllStudentEvents(Long studentId);
    List<EventStudentDto> getAllStudentSubjectEvents(Long studentId, Long subjectId);
    List<EventStudentDto> getActiveStudentEvents(Long studentId);
    List<EventStudentDto> getActiveStudentSubjectEvents(Long studentId, Long subjectId);
    EventStudentDto getEvent(Long eventId);
}
