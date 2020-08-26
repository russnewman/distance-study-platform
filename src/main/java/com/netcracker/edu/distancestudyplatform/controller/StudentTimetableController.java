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
            return scheduleServiceImpl.getAnyDaySchedule(studentId, weekDay, weekIsOdd);
    }

    @GetMapping("/today/{studentId}/{weekIsOdd}")
    public List<ScheduleDto> getTodaySchedule(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "weekIsOdd", required = false) Optional<Boolean> weekIsOdd) {
        return scheduleServiceImpl.getTodaySchedule(studentId, weekIsOdd);
    }

    @GetMapping("/currentEvent/{studentId}/{weekIsOdd}")
    public SubjectDto getCurrentSubject(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "weekIsOdd") Boolean weekIsOdd) {
        return scheduleServiceImpl.getCurrentEvent(studentId, weekIsOdd).getSubjectDto();
    }
}
