package com.netcracker.edu.distancestudyplatform.controller;


import com.netcracker.edu.distancestudyplatform.dto.ScheduleDTO;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDTO;
import com.netcracker.edu.distancestudyplatform.service.impl.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/schedule")
public class StudentTimetableController {
    private final ScheduleService scheduleService;

    @Autowired
    public StudentTimetableController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/full/{studentId}")
    public List<ScheduleDTO> getFullSchedule(@PathVariable(value = "studentId") Long studentId) {
        return scheduleService.getStudentSchedule(studentId);
    }

    @GetMapping({"/full/{studentId}/{day}", "/full/{studentId}/{day}/{weekIsOdd}"})
    public List<ScheduleDTO> getAnyDaySchedule(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "day") String dayName,
            @PathVariable(value = "weekIsOdd", required = false) Optional<Boolean> weekIsOdd) {
            return scheduleService.getAnyDaySchedule(studentId, dayName, weekIsOdd);
    }

    @GetMapping("/today/{studentId}/{weekIsOdd}")
    public List<ScheduleDTO> getTodaySchedule(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "weekIsOdd", required = false) Optional<Boolean> weekIsOdd) {
        return scheduleService.getTodaySchedule(studentId, weekIsOdd);
    }

    @GetMapping("/current_event/{studentId}/{weekIsOdd}")
    public SubjectDTO getCurrentSubject(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "weekIsOdd") Boolean weekIsOdd) {
        return scheduleService.getCurrentEvent(studentId, weekIsOdd).getSubjectDTO();
    }
}
