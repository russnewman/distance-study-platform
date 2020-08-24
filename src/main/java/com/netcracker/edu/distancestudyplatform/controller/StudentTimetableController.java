package com.netcracker.edu.distancestudyplatform.controller;


import com.netcracker.edu.distancestudyplatform.dto.ScheduleDTO;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDTO;
import com.netcracker.edu.distancestudyplatform.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
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
    public List<ScheduleDTO> getFullSchedule(@PathVariable(value = "studentId") Long studentId) {
        return scheduleServiceImpl.getStudentSchedule(studentId);
    }

    @GetMapping({"/full/{studentId}/{day}", "/full/{studentId}/{day}/{weekIsOdd}"})
    public List<ScheduleDTO> getAnyDaySchedule(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "day") String weekDay,
            @PathVariable(value = "weekIsOdd", required = false) Optional<Boolean> weekIsOdd) {
            return scheduleServiceImpl.getAnyDaySchedule(studentId, weekDay, weekIsOdd);
    }

    @GetMapping("/today/{studentId}/{weekIsOdd}")
    public List<ScheduleDTO> getTodaySchedule(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "weekIsOdd", required = false) Optional<Boolean> weekIsOdd) {
        return scheduleServiceImpl.getTodaySchedule(studentId, weekIsOdd);
    }

    @GetMapping("/current_event/{studentId}/{weekIsOdd}")
    public SubjectDTO getCurrentSubject(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "weekIsOdd") Boolean weekIsOdd) {
        return scheduleServiceImpl.getCurrentEvent(studentId, weekIsOdd).getSubjectDTO();
    }
}
