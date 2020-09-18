package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.model.Group;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<ScheduleDto> getGroupSchedule(Group studentGroup);
    List<ScheduleDto> getStudentSchedule(Long studentId);
    List<ScheduleDto> getAnyDaySchedule(Long studentId, String weekDay, Boolean weekIsOdd);
    List<ScheduleDto> getAnyDaySchedule(Long studentId, String weekDay);
    List<ScheduleDto> getTodaySchedule(Long studentId);
    List<ScheduleDto> getNextDaySchedule(Long studentId);
    ScheduleDto getNextEvent(Long studentId);
    ScheduleDto getCurrentEvent(Long studentId);
    ScheduleDto getDayTimeEvent(Long studentId, String weekDay, Boolean weekIsOdd, LocalTime time);
    List<ScheduleDto> getSubjectStudentSchedule(Long studentId, Long subjectId);


    //----------------------------------------------------------//
    //Methods need for teacherTT functionality//


    List<ScheduleDto> getTeacherSchedule(Long teacherId, Optional<Boolean> weekIsOddOptional);
    List<ScheduleDto> getTomorrowTeacherSchedule(Long teacherId, Optional<Boolean> weekIsOddOptional);
    List<ScheduleDto> getSubjectTeacherSchedule(List<ScheduleDto> list, Long subjectId);
    List<ScheduleDto> getSubjectTeacherSchedule(Long teacherId, String subjectName);

}
