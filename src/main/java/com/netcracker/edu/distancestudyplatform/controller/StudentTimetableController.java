package com.netcracker.edu.distancestudyplatform.controller;


import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schedule")
public class StudentTimetableController {
    private final ScheduleServiceImpl scheduleServiceImpl;

    @Autowired
    public StudentTimetableController(ScheduleServiceImpl scheduleServiceImpl) {
        this.scheduleServiceImpl = scheduleServiceImpl;
    }

    @GetMapping("/full/{studentId}")
    public List<ScheduleDto> getFullSchedule(@PathVariable(value = "studentId") Long studentId) {
        return scheduleServiceImpl.getStudentSchedule(studentId);
    }

    @GetMapping({"/full/{studentId}/{day}", "/full/{studentId}/{day}/{weekIsOdd}"})
    public List<ScheduleDto> getAnyDaySchedule(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "day") String weekDay,
            @PathVariable(value = "weekIsOdd", required = false) Optional<Boolean> weekIsOdd) {
        if(weekIsOdd.isPresent()) return scheduleServiceImpl.getAnyDaySchedule(studentId, weekDay, weekIsOdd.get());
            return scheduleServiceImpl.getAnyDaySchedule(studentId, weekDay);
    }

    @GetMapping("/today/{studentId}")
    public List<ScheduleDto> getTodaySchedule(
            @PathVariable(value = "studentId") Long studentId) {
        return scheduleServiceImpl.getTodaySchedule(studentId);
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
}
