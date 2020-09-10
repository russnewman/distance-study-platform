package com.netcracker.edu.distancestudyplatform.ui.controller;

import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.dto.DatabaseFileDto;
import com.netcracker.edu.distancestudyplatform.ui.dto.EventDto;

import com.netcracker.edu.distancestudyplatform.ui.service.EventUiService;
import com.netcracker.edu.distancestudyplatform.ui.service.GroupUiService;
import com.netcracker.edu.distancestudyplatform.ui.service.SubjectUiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;


@Controller
public class HomeworkControllerUi {

    private final EventUiService eventUiService;
    private final SubjectUiService subjectUiService;
    private final GroupUiService groupService;
    private String baseUrl = "http://localhost:8080/";


    @Autowired
    public HomeworkControllerUi(EventUiService eventUiService, SubjectUiService subjectUiService, GroupUiService groupService) {
        this.eventUiService = eventUiService;
        this.subjectUiService = subjectUiService;
        this.groupService = groupService;
    }


    @GetMapping("/teacherHomework/{teacherId}")
    public String homework(@PathVariable Long teacherId, Model model){
        model.addAttribute(teacherId);
        return "teacherHomework-main";
    }



    @GetMapping("/teacherHomework/addEvent/{teacherId}/{subjectName}")
    public String eventAdd(@PathVariable Long teacherId,
                           @PathVariable String subjectName,Model model){

        model.addAttribute("teacherId", teacherId);
        model.addAttribute("subjectName", subjectName);
        model.addAttribute("groups", groupService.findGroupsByTeacherAndSubject(teacherId, subjectName));
        return "teacherHomework-add";
    }




    @GetMapping("/teacherHomework/getEvents/{teacherId}")
    public String getEvents(@PathVariable Long teacherId,
                            @RequestParam Optional<String> sortingTypeOptional,
                            @RequestParam Optional<String> subjectNameOptional,
                            Model model){


        String sortingType = sortingTypeOptional.orElse("addSort");
        String subjectName = subjectNameOptional.orElse("all");


        model.addAttribute("events", eventUiService.getEvents(teacherId, sortingType, subjectName));
        model.addAttribute("subjects", subjectUiService.getSubjectsByTeacherId(teacherId));
        model.addAttribute("dateTimeFormatter", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        model.addAttribute("baseUrl", baseUrl);

        model.addAttribute("sortingType", sortingType);
        model.addAttribute("subjectName", subjectName);

        return "teacherHomework-getEvents";
    }


    @GetMapping("/teacherHomework/showSubjects/{teacherId}/{action}")
    public String showSubjects(@PathVariable Long teacherId, Model model, @PathVariable String action){
        model.addAttribute("subjects", subjectUiService.getSubjectsByTeacherId(teacherId));
        model.addAttribute("action", action);
        return "teacherHomework-chooseSubject";
    }


    @GetMapping("/teacherHomework/editEvent/{teacherId}/{eventId}")
    public String editEvent(@PathVariable Long teacherId, @PathVariable Long eventId, Model model){


        Event event = eventUiService.getEventById(eventId);
        model.addAttribute("event", event);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("groups", groupService.findGroupsByTeacherAndSubject(teacherId, event.getSubject().getName()));
        model.addAttribute("dateTimeFormatter", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        model.addAttribute("baseUrl", baseUrl);

        return "teacherHomework-editEvent";
    }



    @PostMapping("/teacherHomework/deleteEvent/{teacherId}/{eventId}")
    public String deleteEvent(@PathVariable Long teacherId,
                              @PathVariable Long eventId,
                              @RequestParam("sortingType") String sortingType,
                              @RequestParam("subjectName") String subjectName){
        eventUiService.deleteEvent(eventId);


        String baseUrl = "http://localhost:8080/";
        String URL = baseUrl + "teacherHomework/getEvents/" + teacherId.toString();
        System.out.println("SortingType" + sortingType);
        System.out.println("Sb" + subjectName);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("sortingTypeOptional", sortingType)
                .queryParam("subjectNameOptional", subjectName);

        return "redirect:" +  builder.toUriString();
    }


    @PostMapping("/teacherHomework/editEvent/{teacherId}/{eventId}")
    public String editEvent(@PathVariable Long teacherId,
                            @PathVariable Long eventId,
                            @RequestParam String groupName,
                            @RequestParam String description,
                            @RequestParam String endTime,
                            @RequestParam Optional<MultipartFile> fileOptional) throws IOException {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = new Date();
        try {
            endDate = df.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        EventDto eventDto = new EventDto();
        eventDto.setGroupName(groupName);
        eventDto.setDescription(description);
        eventDto.setEndTime(endDate);

        if (fileOptional.isPresent()){
            System.out.println("TRUE");
            String fileName = StringUtils.cleanPath(fileOptional.get().getOriginalFilename());
            DatabaseFileDto databaseFileDto = new DatabaseFileDto(fileName, fileOptional.get().getContentType(),
                    fileOptional.get().getBytes());
            eventDto.setDatabaseFileDto(databaseFileDto);
        }

        eventUiService.editEvent(eventId, eventDto);

        return "redirect:/teacherHomework/getEvents/{teacherId}";
    }




    @PostMapping("/teacherHomework/addEvent/{teacherId}")
    public String eventPostAdd(
            @PathVariable Long teacherId,
            @RequestParam String subjectName,
            @RequestParam String groupName,
            @RequestParam String description,
            @RequestParam String endTime,
            @RequestParam Optional<MultipartFile> fileOptional) throws IOException {



        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = new Date();
        try {
            endDate = df.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        EventDto eventDto = new EventDto();
        eventDto.setTeacherId(teacherId);
        eventDto.setSubjectName(subjectName);
        eventDto.setGroupName(groupName);
        eventDto.setDescription(description);
        eventDto.setStartTime(new Date());
        eventDto.setEndTime(endDate);


        if (fileOptional.isPresent()){
            String fileName = StringUtils.cleanPath(fileOptional.get().getOriginalFilename());
            DatabaseFileDto databaseFileDto = new DatabaseFileDto(fileName, fileOptional.get().getContentType(),
                                                                            fileOptional.get().getBytes());
            eventDto.setDatabaseFileDto(databaseFileDto);
        }


        eventUiService.saveEventDto(eventDto);

        return "redirect:/teacherHomework/{teacherId}";
    }
}
