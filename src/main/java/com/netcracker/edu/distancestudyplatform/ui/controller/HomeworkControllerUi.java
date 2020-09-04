package com.netcracker.edu.distancestudyplatform.ui.controller;

import com.netcracker.edu.distancestudyplatform.ui.dto.DatabaseFileDto;
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

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Controller
public class HomeworkControllerUi {

    private final EventUiService eventUiService;
    private final SubjectUiService subjectUiService;
    private final GroupUiService groupService;


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



    @GetMapping("/teacherHomework/addEvent/{teacherId}")
    public String eventAdd(@PathVariable Long teacherId,
                           @RequestParam String subjectName,Model model){

        model.addAttribute("teacherId", teacherId);
        model.addAttribute("subjectName", subjectName);
        model.addAttribute("groups", groupService.findGroupsByTeacherAndSubject(teacherId, subjectName));
        return "teacherHomework-add";
    }



    @GetMapping("/teacherHomework/showSubjects/{teacherId}")
    public String showSubjects(@PathVariable Long teacherId, Model model){
        model.addAttribute("subjects", subjectUiService.getSubjectsByTeacherId(teacherId));
        return "teacherHomework-showSubjects";
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
