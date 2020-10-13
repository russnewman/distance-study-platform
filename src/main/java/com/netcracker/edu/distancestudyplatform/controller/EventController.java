package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.dto.event.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.dto.event.GetStudentEventsRequestDto;
import com.netcracker.edu.distancestudyplatform.dto.event.GetStudentEventsResponseDto;
import com.netcracker.edu.distancestudyplatform.mappers.EventStudentDtoMapper;
import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {
    final private EventService eventService;
    final private StudentService studentService;

    public  EventController(EventService eventService, StudentService studentService){
        this.eventService = eventService;
        this.studentService = studentService;
    }

    @GetMapping
    public GetStudentEventsResponseDto getAllStudentEvents(@RequestParam(required = false) Long subjectId,
                                                           @RequestParam Long studentId,
                                                           @PageableDefault(sort = {"endDate"}) Pageable pageable) {
        Long groupId = studentService.findById(studentId).getGroup().getId();
        Page<Event> events;
        if(subjectId != null) {
            events = eventService.getAllGroupSubjectEvents(groupId, subjectId, pageable);
        } else {
            events = eventService.getAllGroupEvents(groupId, pageable);
        }
        return new GetStudentEventsResponseDto(events.stream()
                .map(EventStudentDtoMapper.INSTANCE::toDTO).collect(Collectors.toList()), events.getTotalPages());
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
