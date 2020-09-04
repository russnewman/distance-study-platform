package com.netcracker.edu.distancestudyplatform.controller;


import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.service.impl.ScheduleServiceImpl;
import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StudentTimetableController {
    private final ScheduleServiceImpl scheduleServiceImpl;

    @Autowired
    public StudentTimetableController(ScheduleServiceImpl scheduleServiceImpl) {
        this.scheduleServiceImpl = scheduleServiceImpl;
    }

    @GetMapping("/full")
    public ScheduleDtoList getFullSchedule(@RequestParam(value = "studentId") Long studentId) {
        return new ScheduleDtoList(scheduleServiceImpl.getStudentSchedule(studentId));
    }

    @GetMapping({"/full/{studentId}/{day}", "/full/{studentId}/{day}/{weekIsOdd}"})
    public ScheduleDtoList getAnyDaySchedule(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "day") String weekDay,
            @PathVariable(value = "weekIsOdd", required = false) Optional<Boolean> weekIsOdd) {
        return weekIsOdd.map(
                oddWeek ->
                        new ScheduleDtoList(
                                scheduleServiceImpl.getAnyDaySchedule(studentId, weekDay, oddWeek)))
                .orElseGet(() ->
                        new ScheduleDtoList(
                                scheduleServiceImpl.getAnyDaySchedule(studentId, weekDay))
                );
    }

    @GetMapping("/todayStudentSchedule")
    public ScheduleDtoList getTodaySchedule(
            @RequestParam(value = "studentId") Long studentId) {
        return new ScheduleDtoList(scheduleServiceImpl.getTodaySchedule(studentId));
    }

    @GetMapping("/tomorrowStudentSchedule")
    public ScheduleDtoList getNextDaySchedule(
            @RequestParam(value = "studentId") Long studentId) {
        return new ScheduleDtoList(scheduleServiceImpl.getNextDaySchedule(studentId));
    }

    @GetMapping("/currentStudentSubject")
    public SubjectDto getCurrentSubject(
            @RequestParam(value = "studentId") Long studentId) {
        return scheduleServiceImpl.getCurrentEvent(studentId).getSubjectDto();
    }

    @GetMapping("/nextStudentSubject")
    public SubjectDto getNextSubject(@RequestParam(value = "studentId") Long studentId){
        return scheduleServiceImpl.getNextEvent(studentId).getSubjectDto();
    }

    @GetMapping("/getSubjectStudentSchedule")
    public ScheduleDtoList getSubjectStudentSchedule(@RequestParam(name = "studentId") Long studentId,
                                                     @RequestParam(name = "subjectId") Long subjectId
                                                     ){
        return new ScheduleDtoList(scheduleServiceImpl.getSubjectStudentSchedule(studentId, subjectId));
    }
}
