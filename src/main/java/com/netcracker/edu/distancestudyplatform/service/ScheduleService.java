package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDTO;
import com.netcracker.edu.distancestudyplatform.model.Group;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<ScheduleDTO> getGroupSchedule(Group studentGroup);
    List<ScheduleDTO> getStudentSchedule(Long studentId);
    List<ScheduleDTO> getAnyDaySchedule(Long studentId, String weekDay, Optional<Boolean> weekIsOdd);
    List<ScheduleDTO> getTodaySchedule(Long studentId, Optional<Boolean> weekIsOdd);
    ScheduleDTO getCurrentEvent(Long studentId, Boolean weekIsOdd);
    ScheduleDTO getDayTimeEvent(Long studentId, String weekDay, Boolean weekIsOdd, LocalTime time);
}
