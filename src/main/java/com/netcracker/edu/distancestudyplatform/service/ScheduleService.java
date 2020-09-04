package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Schedule;

import java.time.LocalTime;
import java.util.List;

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


    List<Schedule> getTeacherSchedule(Long teacherId);
    List<Schedule> getTomorrowTeacherSchedule(Long teacherId);
    List<Schedule> getTeacherSchedule(Long teacherId, Boolean weekIsOdd);
    List<Schedule> getTomorrowTeacherSchedule(Long teacherId, Boolean weekIsOdd);
    List<Schedule> getSubjectTeacherSchedule(List<Schedule> list, Long subjectId);
    List<Schedule> getSubjectTeacherSchedule(Long teacherId, String subjectName);

}
