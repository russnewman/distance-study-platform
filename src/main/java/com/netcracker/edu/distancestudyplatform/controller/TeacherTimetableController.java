package com.netcracker.edu.distancestudyplatform.controller;


import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.dto.wrappers.ScheduleDtoList;
import com.netcracker.edu.distancestudyplatform.service.ScheduleService;
import com.netcracker.edu.distancestudyplatform.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
public class TeacherTimetableController {

    private final ScheduleService scheduleService;
    private final SubjectService subjectService;


    @Autowired
    public TeacherTimetableController(ScheduleService scheduleService, SubjectService subjectService) {
        this.scheduleService = scheduleService;
        this.subjectService = subjectService;
    }

    @GetMapping("/teacherWeekSchedule")
    public ScheduleDtoList getTeacherSchedule(@RequestParam("teacherId") Long teacherId,
                                              @RequestParam Optional<Boolean> weekIsOddOptional){
        return new ScheduleDtoList(scheduleService.getTeacherSchedule(teacherId, weekIsOddOptional));
    }


    @GetMapping("/tomorrowTeacherWeekSchedule")
    public ScheduleDtoList getTomorrowTeacherSchedule(@RequestParam Long teacherId, @RequestParam Optional<Boolean> weekIsOddOptional){
        return new ScheduleDtoList(scheduleService.getTomorrowTeacherSchedule(teacherId, weekIsOddOptional));
    }


    @PostMapping("/subjectTeacherSchedule")
    public ScheduleDtoList getTeacherScheduleBySubject(@RequestBody List<ScheduleDto> schedules,
                                                    @RequestParam("subjectId") Long subjectId) {
        return new ScheduleDtoList(scheduleService.getSubjectTeacherSchedule(schedules, subjectId));
    }

}
