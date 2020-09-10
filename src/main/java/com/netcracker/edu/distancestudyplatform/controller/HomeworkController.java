package com.netcracker.edu.distancestudyplatform.controller;


import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.GroupService;
import com.netcracker.edu.distancestudyplatform.service.wrappers.EventList;
import com.netcracker.edu.distancestudyplatform.service.wrappers.GroupList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
public class HomeworkController {

    private final EventService eventService;
    private final GroupService groupService;

    @Autowired
    public HomeworkController(EventService eventService, GroupService groupService) {
        this.eventService = eventService;
        this.groupService = groupService;
    }


    @PostMapping("/saveEvent")
    public void saveEvent(@RequestBody EventDto eventDto){
        eventService.saveEvent(eventDto);
    }





    @PostMapping("/editEvent")
    public void editEvent(@RequestParam Long eventId,
                          @RequestBody EventDto eventDto){

        eventService.editEvent(eventId, eventDto);
    }


    @PostMapping("/deleteEvent")
    public void deleteEvent(@RequestBody Long eventId){
        eventService.deleteEvent(eventId);
    }


    @GetMapping("/getEventById")
    public Event getEventById(@RequestParam Long eventId){
        return eventService.getEventById(eventId);
    }

    @GetMapping("/getEvents")
    public EventList getEvents(@RequestParam("teacherId") Long teacherId,
                               @RequestParam("sortingType") String sortingType,
                               @RequestParam("subjectName") String subjectName){

        sortingType = java.net.URLDecoder.decode(sortingType, StandardCharsets.UTF_8);
        subjectName = java.net.URLDecoder.decode(subjectName, StandardCharsets.UTF_8);
        return new EventList(eventService.getEvents(teacherId, sortingType, subjectName));
    }


    @GetMapping("/findGroupsByTeacherAndSubject")
    public GroupList findGroupsByTeacherAndSubject(@RequestParam("teacherId") Long teacherId,
                                                   @RequestParam("subjectName") String subjectName){

        return new GroupList(groupService.findGroupsByTeacherAndSubject(teacherId, subjectName));
    }
}
