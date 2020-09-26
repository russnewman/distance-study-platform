package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.dto.ScheduleVDto;
import com.netcracker.edu.distancestudyplatform.model.Group;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<ScheduleVDto> getGroupSchedule(Group studentGroup);
    List<ScheduleVDto> getStudentSchedule(Long studentId);
    List<ScheduleVDto> getAnyDaySchedule(Long studentId, String weekDay, Boolean weekIsOdd);
    List<ScheduleVDto> getAnyDaySchedule(Long studentId, String weekDay);
    List<ScheduleVDto> getTodaySchedule(Long studentId);
    List<ScheduleVDto> getNextDaySchedule(Long studentId);
    ScheduleVDto getNextEvent(Long studentId);
    ScheduleVDto getCurrentEvent(Long studentId);
    ScheduleVDto getDayTimeEvent(Long studentId, String weekDay, Boolean weekIsOdd, LocalTime time);
    List<ScheduleVDto> getSubjectStudentSchedule(Long studentId, Long subjectId);


    //----------------------------------------------------------//
    //Methods need for teacherTT functionality//


    List<ScheduleDto> getTeacherSchedule(Long teacherId, Optional<Boolean> weekIsOddOptional);
    List<ScheduleDto> getTomorrowTeacherSchedule(Long teacherId, Optional<Boolean> weekIsOddOptional);
    List<ScheduleDto> getSubjectTeacherSchedule(List<ScheduleDto> list, Long subjectId);
    List<ScheduleDto> getSubjectTeacherSchedule(Long teacherId, String subjectName);

}
