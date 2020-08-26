package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.model.Group;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<ScheduleDto> getGroupSchedule(Group studentGroup);
    List<ScheduleDto> getStudentSchedule(Long studentId);
    List<ScheduleDto> getAnyDaySchedule(Long studentId, String weekDay, Optional<Boolean> weekIsOdd);
    List<ScheduleDto> getTodaySchedule(Long studentId, Optional<Boolean> weekIsOdd);
    ScheduleDto getCurrentEvent(Long studentId, Boolean weekIsOdd);
    ScheduleDto getDayTimeEvent(Long studentId, String weekDay, Boolean weekIsOdd, LocalTime time);
}
