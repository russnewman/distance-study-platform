package com.netcracker.edu.distancestudyplatform.controller;
import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.dto.EventDtoList;
import com.netcracker.edu.distancestudyplatform.dto.EventFormDto;
import com.netcracker.edu.distancestudyplatform.dto.GroupDtoList;
import com.netcracker.edu.distancestudyplatform.service.AssignmentService;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.GroupService;
import com.netcracker.edu.distancestudyplatform.service.wrappers.AssignmentList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;



@RestController
public class HomeworkController {

    private final EventService eventService;
    private final GroupService groupService;
    private final AssignmentService assignmentService;

    @Autowired
    public HomeworkController(EventService eventService, GroupService groupService, AssignmentService assignmentService) {
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



    @GetMapping("/getAssignments")
    public AssignmentList getAssignments(@RequestParam Long eventId){
        return new AssignmentList(assignmentService.getAssignmentByEvent(eventId));
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
        return new EventDtoList(eventService.getEvents(teacherId, sortingType, subjectName));
    }



    @GetMapping("/findGroupsByTeacherAndSubject")
    public GroupDtoList findGroupsByTeacherAndSubject(@RequestParam("teacherId") Long teacherId,
                                                   @RequestParam("subjectName") String subjectName){


        subjectName = java.net.URLDecoder.decode(subjectName, StandardCharsets.UTF_8);
        return new GroupDtoList(groupService.findGroupsByTeacherAndSubject(teacherId, subjectName));
    }
}
