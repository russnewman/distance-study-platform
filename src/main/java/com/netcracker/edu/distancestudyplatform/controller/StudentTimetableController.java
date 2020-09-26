package com.netcracker.edu.distancestudyplatform.controller;


import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.dto.ScheduleVDto;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.dto.wrappers.ScheduleDtoList;
import com.netcracker.edu.distancestudyplatform.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentTimetableController {
    private final ScheduleServiceImpl scheduleServiceImpl;

    @Autowired
    public StudentTimetableController(ScheduleServiceImpl scheduleServiceImpl) {
        this.scheduleServiceImpl = scheduleServiceImpl;
    }

    @GetMapping("/full")
    public List<ScheduleVDto> getFullSchedule(@RequestParam(value = "studentId") Long studentId) {
        return scheduleServiceImpl.getStudentSchedule(studentId);
    }

    @GetMapping({"/full/{studentId}/{day}", "/full/{studentId}/{day}/{weekIsOdd}"})
    public List<ScheduleVDto> getAnyDaySchedule(
            @PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "day") String weekDay,
            @PathVariable(value = "weekIsOdd", required = false) Optional<Boolean> weekIsOdd) {
        return weekIsOdd.map(
                oddWeek -> scheduleServiceImpl.getAnyDaySchedule(studentId, weekDay, oddWeek))
                .orElseGet(() -> scheduleServiceImpl.getAnyDaySchedule(studentId, weekDay)
                );
    }

    @GetMapping("/todayStudentSchedule")
    public List<ScheduleVDto> getTodaySchedule(
            @RequestParam(value = "studentId") Long studentId) {
        return scheduleServiceImpl.getTodaySchedule(studentId);
    }

    @GetMapping("/tomorrowStudentSchedule")
    public List<ScheduleVDto> getNextDaySchedule(
            @RequestParam(value = "studentId") Long studentId) {
        return scheduleServiceImpl.getNextDaySchedule(studentId);
    }

    @GetMapping("/currentStudentSubject")
    public String getCurrentSubject(
            @RequestParam(value = "studentId") Long studentId) {
        return scheduleServiceImpl.getCurrentEvent(studentId).getSubject();
    }

    @GetMapping("/nextStudentSubject")
    public String getNextSubject(@RequestParam(value = "studentId") Long studentId){
        return scheduleServiceImpl.getNextEvent(studentId).getSubject();
    }

    @GetMapping("/getSubjectStudentSchedule")
    public List<ScheduleVDto> getSubjectStudentSchedule(@RequestParam(name = "studentId") Long studentId,
                                                     @RequestParam(name = "subjectId") Long subjectId
                                                     ){
        return scheduleServiceImpl.getSubjectStudentSchedule(studentId, subjectId);
    }
}
