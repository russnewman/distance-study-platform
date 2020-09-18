package com.netcracker.edu.distancestudyplatform.controller;
import com.netcracker.edu.distancestudyplatform.dto.*;
import com.netcracker.edu.distancestudyplatform.dto.wrappers.AssignmentDtoList;
import com.netcracker.edu.distancestudyplatform.dto.wrappers.EventDtoList;
import com.netcracker.edu.distancestudyplatform.dto.wrappers.GroupDtoList;
import com.netcracker.edu.distancestudyplatform.service.AssignmentService;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherHomeworkController {

    private final EventService eventService;
    private final GroupService groupService;
    private final AssignmentService assignmentService;

    @Autowired
    public TeacherHomeworkController(EventService eventService, GroupService groupService, AssignmentService assignmentService) {
        this.eventService = eventService;
        this.groupService = groupService;
        this.assignmentService = assignmentService;
    }


    @PostMapping("/saveEvent")
    public void saveEvent(@RequestBody EventFormDto eventFormDto){
        eventService.saveEvent(eventFormDto);
    }


    @PostMapping("/editEvent")
    public void editEvent(@RequestParam Long eventId,
                          @RequestBody EventFormDto eventFormDto){

        eventService.editEvent(eventId, eventFormDto);
    }

    @PostMapping("/deleteEvent")
    public void deleteEvent(@RequestBody Long eventId){
        eventService.deleteEvent(eventId);
    }



    @GetMapping("/getAssignmentsByEvent")
    public AssignmentDtoList getAssignments(@RequestParam Long eventId){
        return new AssignmentDtoList(assignmentService.getAssignmentsByEvent(eventId));
    }



    @GetMapping("/getEventById")
    public EventDto getEventById(@RequestParam Long eventId){
        return eventService.getEventById(eventId);
    }



    @GetMapping("/getEvents")
    public EventDtoList getEvents(@RequestParam("teacherId") Long teacherId,
                                                        @RequestParam("sortingType") String sortingType,
                                                        @RequestParam("subjectName") String subjectName){

        sortingType = java.net.URLDecoder.decode(sortingType, StandardCharsets.UTF_8);
        subjectName = java.net.URLDecoder.decode(subjectName, StandardCharsets.UTF_8);


        List<EventDto> events = eventService.getEvents(teacherId, sortingType, subjectName);

        for(EventDto event: events)
           event.setCanDeleteEvent(eventService.canDeleteEvent(event.getId()));

        return new EventDtoList(events);
    }


    @GetMapping("/findGroupsByTeacherAndSubject")
    public GroupDtoList findGroupsByTeacherAndSubject(@RequestParam("teacherId") Long teacherId,
                                                      @RequestParam("subjectName") String subjectName){


        subjectName = java.net.URLDecoder.decode(subjectName, StandardCharsets.UTF_8);
        return new GroupDtoList(groupService.findGroupsByTeacherAndSubject(teacherId, subjectName));
    }


}
