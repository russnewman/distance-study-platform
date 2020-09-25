package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.mappers.EventMapper;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {
    final private EventService eventService;

    public  EventController (EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventStudentDto> getAllStudentEvents(
            @RequestParam Long studentId,
            @RequestParam(required = false) Optional<Long> subjectId) {
        if(subjectId.isPresent()){
            return eventService.getAllStudentSubjectEvents(studentId, subjectId.get());
        }
        return eventService.getAllStudentEvents(studentId);
    }

    @GetMapping("/active")
    public List<EventStudentDto> getAllActiveStudentEvents(
            @RequestParam Long studentId,
            @RequestParam(required = false) Optional<Long> subjectId) {
        if(subjectId.isPresent()){
            return eventService.getAllActiveStudentSubjectEvents(studentId, subjectId.get());
        }
        return eventService.getAllActiveStudentEvents(studentId);
    }

    @GetMapping("/{eventId}/")
    public EventStudentDto getEvent(@PathVariable Long eventId){
        return eventService.getEventDtoById(eventId);
    }
}
