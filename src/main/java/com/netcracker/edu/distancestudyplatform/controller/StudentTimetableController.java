package com.netcracker.edu.distancestudyplatform.controller;


import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.service.impl.ScheduleServiceImpl;
import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/schedule")
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

    @GetMapping("/today/{studentId}")
    public ScheduleDtoList getTodaySchedule(
            @PathVariable(value = "studentId") Long studentId) {
        return new ScheduleDtoList(scheduleServiceImpl.getTodaySchedule(studentId));
    }

    @GetMapping("/tomorrow/{studentId}")
    public ScheduleDtoList getNextDaySchedule(
            @PathVariable(value = "studentId") Long studentId) {
        return new ScheduleDtoList(scheduleServiceImpl.getNextDaySchedule(studentId));
    }

    @GetMapping("/currentEvent/{studentId}")
    public SubjectDto getCurrentSubject(
            @PathVariable(value = "studentId") Long studentId) {
        return scheduleServiceImpl.getCurrentEvent(studentId).getSubjectDto();
    }

    @GetMapping("/nextEvent/{studentId}")
    public SubjectDto getNextSubject(@PathVariable(value = "studentId") Long studentId){
        return scheduleServiceImpl.getNextEvent(studentId).getSubjectDto();
    }

    @PostMapping("/scheduleByDate")
    public SubjectDto searchScheduleWithParameters(){
        return null;
    }
}
