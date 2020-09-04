package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.mappers.ScheduleMapper;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.repository.ScheduleRepository;
import com.netcracker.edu.distancestudyplatform.service.ScheduleService;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import com.netcracker.edu.distancestudyplatform.utils.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final StudentService studentService;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, StudentService studentService) {
        this.scheduleRepository = scheduleRepository;
        this.studentService = studentService;
    }

    public List<ScheduleDto> getGroupSchedule(Group studentGroup) {
        return ScheduleUtils.castSchedulesToDTO(
                scheduleRepository.findByGroupId(studentGroup.getId())
                .orElseGet(ArrayList::new)
        );
    }

    public List<ScheduleDto> getStudentSchedule(Long studentId) {
        return getGroupSchedule(
                studentService.getStudentGroup(studentId)
        );
    }

    public List<ScheduleDto> getAnyDaySchedule(Long studentId, String weekDay, Boolean weekIsOdd){
        return ScheduleUtils.castSchedulesToDTO(
                scheduleRepository.findByDayNameAndGroupIdAndWeekIsOdd(
                    DayOfWeek.valueOf(weekDay.toUpperCase()),
                    studentService.getStudentGroup(studentId).getId(),
                    weekIsOdd
                )
                .orElseGet(ArrayList::new)
        );
    }

    public List<ScheduleDto> getAnyDaySchedule(Long studentId, String weekDay){
        return ScheduleUtils.castSchedulesToDTO(
                scheduleRepository.findByDayNameAndGroupId(
                    DayOfWeek.valueOf(weekDay.toUpperCase()),
                    studentService.getStudentGroup(studentId).getId()
                )
                .orElseGet(ArrayList::new)
        );
    }

    public List<ScheduleDto> getNextDaySchedule(Long studentId){
        return getAnyDaySchedule(studentId, ScheduleUtils.getNextDayName(), ScheduleUtils.getWeekIsOdd());
    }
    public List<ScheduleDto> getTodaySchedule(Long studentId){
        return getAnyDaySchedule(studentId, ScheduleUtils.getTodayName(), ScheduleUtils.getWeekIsOdd());
    }

    public ScheduleDto getCurrentEvent(Long studentId){
        return getDayTimeEvent(studentId, ScheduleUtils.getTodayName(), ScheduleUtils.getWeekIsOdd(), LocalTime.now());
    }

    public List<ScheduleDto> getSubjectStudentSchedule(Long studentId, Long subjectId){
        return ScheduleUtils.castSchedulesToDTO(
                scheduleRepository.findBySubject_IdAndGroup_Id(
                        subjectId, studentService.getStudentGroup(studentId).getId()
                )
                        .orElseGet(ArrayList::new)
        );
    }

    public ScheduleDto getDayTimeEvent(Long studentId, String weekDay, Boolean weekIsOdd, LocalTime time){
        return ScheduleMapper.INSTANCE.toDTO(
                scheduleRepository
                        .findFirstByClassTime_StartTimeLessThanEqualAndClassTime_EndTimeGreaterThanEqualAndDayNameAndGroupIdAndWeekIsOdd(
                            time,
                            time,
                            DayOfWeek.valueOf(weekDay.toUpperCase()),
                            studentService.getStudentGroup(studentId).getId(),
                            weekIsOdd
                ).orElseGet(Schedule::new)
        );
    }

    public ScheduleDto getNextEvent(Long studentId){
        return ScheduleMapper.INSTANCE.toDTO(
                scheduleRepository.findFirstByClassTime_StartTimeGreaterThanAndDayNameAndGroupIdAndWeekIsOdd(
                        LocalTime.now(),
                        DayOfWeek.valueOf(ScheduleUtils.getTodayName().toUpperCase()),
                        studentService.getStudentGroup(studentId).getId(),
                        ScheduleUtils.getWeekIsOdd()
                ).orElseGet(Schedule::new)
        );
    }
}
