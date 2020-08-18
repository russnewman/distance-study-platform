package com.netcracker.edu.distancestudyplatform.services;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDTO;
import com.netcracker.edu.distancestudyplatform.mappers.ScheduleMapper;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final
    ScheduleRepository scheduleRepository;

    private final
    StudentService studentService;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, StudentService studentService) {
        this.scheduleRepository = scheduleRepository;
        this.studentService = studentService;
    }

    public List<ScheduleDTO> getGroupSchedule(Group studentGroup) {
        return castOptionalSchedulesToDTO(
                scheduleRepository.findByGroupId(studentGroup.getId())
        );
    }

    public List<ScheduleDTO> getStudentSchedule(Long studentId) {
        return getGroupSchedule(
                studentService.getStudentGroup(studentId)
        );
    }

    public List<ScheduleDTO> getAnyDaySchedule(Long studentId, String dayName, Optional<Boolean> weekIsOdd){
        return castOptionalSchedulesToDTO(
                    weekIsOdd.map(wIsOdd -> scheduleRepository
                            .findByDayNameAndGroupIdAndWeekIsOdd(
                                    dayName, studentService.getStudentGroup(studentId).getId(), wIsOdd
                            )
                    )
                    .orElseGet(() -> scheduleRepository
                            .findByDayNameAndGroupId(
                                    dayName, studentService.getStudentGroup(studentId).getId())
                    )
                );
    }

    public List<ScheduleDTO> getTodaySchedule(Long studentId, Optional<Boolean> weekIsOdd){
        return getAnyDaySchedule(studentId, getTodayName(), weekIsOdd);
    }

    public ScheduleDTO getCurrentEvent(Long studentId, Boolean weekIsOdd){
        return getDayTimeEvent(studentId, getTodayName(), weekIsOdd, LocalTime.now());
    }

    public ScheduleDTO getDayTimeEvent(Long studentId, String dayName, Boolean weekIsOdd, LocalTime time){
        return castOptionalScheduleToDTO(
                scheduleRepository.findByClassTime_StartTimeLessThanEqualAndClassTime_EndTimeGreaterThanEqualAndDayNameAndGroupIdAndWeekIsOdd(
                        time, time, dayName, studentService.getStudentGroup(studentId).getId(), weekIsOdd
                )
        );
    }

    private List<ScheduleDTO> castOptionalSchedulesToDTO(Optional<List<Schedule>> schedules){
        return schedules.orElseGet(ArrayList::new)
                .stream()
                .map(ScheduleMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    private ScheduleDTO castOptionalScheduleToDTO(Optional<Schedule> schedule){
        return ScheduleMapper.INSTANCE.toDTO(schedule.orElseGet(Schedule::new));
    }

    private String getTodayName(){
        return new SimpleDateFormat("EE", Locale.ENGLISH)
                .format(
                        Calendar.getInstance()
                                .getTime()
                                .getTime()
                );
    }
}
