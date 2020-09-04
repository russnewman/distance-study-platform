package com.netcracker.edu.distancestudyplatform.controller;


import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.service.EventService;
import com.netcracker.edu.distancestudyplatform.service.GroupService;
import com.netcracker.edu.distancestudyplatform.ui.service.GroupList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/findGroupsByTeacherAndSubject")
    public GroupList findGroupsByTeacherAndSubject(@RequestParam("teacherId") Long teacherId,
                                                   @RequestParam("subjectName") String subjectName){

        return new GroupList(groupService.findGroupsByTeacherAndSubject(teacherId, subjectName));
    }
}
