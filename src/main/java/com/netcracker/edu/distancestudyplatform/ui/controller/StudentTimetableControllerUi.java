package com.netcracker.edu.distancestudyplatform.ui.controller;

import com.netcracker.edu.distancestudyplatform.service.ScheduleService;
import com.netcracker.edu.distancestudyplatform.ui.service.ScheduleUiService;
import com.netcracker.edu.distancestudyplatform.ui.service.SubjectUiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/studentSchedule")
public class StudentTimetableControllerUi {
    private final ScheduleUiService scheduleUiService;
    private final SubjectUiService subjectUiService;


    @Autowired
    public StudentTimetableControllerUi(ScheduleUiService scheduleUiService, SubjectUiService subjectUiService) {
        this.scheduleUiService = scheduleUiService;
        this.subjectUiService = subjectUiService;
    }

    @GetMapping("/full/{studentId}")
    public String getSchedule(
            @PathVariable("studentId") Long studentId,
            Model model){
        model.addAttribute("schedules", scheduleUiService.getStudentFullSchedule(studentId).getSchedules());
        model.addAttribute("subjects", subjectUiService.getAllSubjects().getSubjects());
        model.addAttribute("todaySchedules", scheduleUiService.getStudentTodaySchedule(studentId).getSchedules());
        model.addAttribute("tomorrowSchedules", scheduleUiService.getStudentTomorrowSchedule(studentId).getSchedules());
        //model.addAttribute("currentSubject", scheduleUiService.getStudentCurrentSubject(studentId));
        //model.addAttribute("nextSubject", scheduleUiService.getStudentNextSubject(studentId));
        return "student_schedule";
    }

    @GetMapping("/subjectSchedule/{studentId}")
    public String getSubjectSchedule(@PathVariable("studentId") Long studentId, @RequestParam("subject") Long subjectId, Model model){
        model.addAttribute("schedules", scheduleUiService.getStudentSubjectSchedule(studentId, subjectId).getSchedules());
        model.addAttribute("subjects", subjectUiService.getAllSubjects().getSubjects());
        return "student_schedule";
    }

    @GetMapping("/test_page")
    public String test(Model model,
                       @RequestParam(name="name", required=false, defaultValue="World") String name){
        model.addAttribute("name", name);
        return "test_page";
    }


}
