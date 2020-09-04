package com.netcracker.edu.distancestudyplatform.controller;


import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.service.ScheduleList;
import com.netcracker.edu.distancestudyplatform.service.ScheduleService;
import com.netcracker.edu.distancestudyplatform.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class TeacherTimetableController {

    private final ScheduleService scheduleService;
    private final SubjectService subjectService;


    @Autowired
    public TeacherTimetableController(ScheduleService scheduleService, SubjectService subjectService) {
        this.scheduleService = scheduleService;
        this.subjectService = subjectService;
    }


    @GetMapping("/teacherSchedule")
    public ScheduleList getTeacherSchedule(@RequestParam("teacherId") Long teacherId){
        return new ScheduleList(scheduleService.getTeacherSchedule(teacherId));
    }



    @GetMapping("/teacherWeekSchedule")
    public ScheduleList getTeacherSchedule(@RequestParam("teacherId") Long teacherId,
                                              @RequestParam Boolean weekIsOdd){
        return new ScheduleList(scheduleService.getTeacherSchedule(teacherId, weekIsOdd));
    }


    @GetMapping("/tomorrowTeacherSchedule")
    public ScheduleList getTomorrowTeacherSchedule(@RequestParam("teacherId") Long teacherId){
        return new ScheduleList(scheduleService.getTomorrowTeacherSchedule(teacherId));
    }


    @GetMapping("/tomorrowTeacherWeekSchedule")
    public ScheduleList getTomorrowTeacherSchedule(@RequestParam Long teacherId, @RequestParam Boolean weekIsOdd){
        return new ScheduleList(scheduleService.getTomorrowTeacherSchedule(teacherId, weekIsOdd));
    }




    @PostMapping(value = "/subjectTeacherSchedule")
    public ScheduleList getTeacherScheduleBySubject(@RequestBody List<Schedule> schedules,
                                                    @RequestParam("subjectId") Long subjectId) {
        return new ScheduleList(scheduleService.getSubjectTeacherSchedule(schedules, subjectId));
    }

}
