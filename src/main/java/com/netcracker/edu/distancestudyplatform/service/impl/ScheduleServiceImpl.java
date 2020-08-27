package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.mappers.ScheduleMapper;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.repository.ScheduleRepository;
import com.netcracker.edu.distancestudyplatform.service.ScheduleService;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

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
        return castOptionalSchedulesToDTO(
                scheduleRepository.findByGroupId(studentGroup.getId())
        );
    }

    public List<ScheduleDto> getStudentSchedule(Long studentId) {
        return getGroupSchedule(
                studentService.getStudentGroup(studentId)
        );
    }

    public List<ScheduleDto> getAnyDaySchedule(Long studentId, String weekDay, Boolean weekIsOdd){
        return castOptionalSchedulesToDTO(
                scheduleRepository.findByDayNameAndGroupIdAndWeekIsOdd(
                        DayOfWeek.valueOf(weekDay.toUpperCase()),
                        studentService.getStudentGroup(studentId).getId(),
                        weekIsOdd
                )
        );
    }

    public List<ScheduleDto> getAnyDaySchedule(Long studentId, String weekDay){
        return castOptionalSchedulesToDTO(
                scheduleRepository.findByDayNameAndGroupId(
                        DayOfWeek.valueOf(weekDay.toUpperCase()),
                        studentService.getStudentGroup(studentId).getId()
                )
        );
    }

    public List<ScheduleDto> getTodaySchedule(Long studentId){
        return getAnyDaySchedule(studentId, getTodayName(), getWeekIsOdd());
    }

    public ScheduleDto getCurrentEvent(Long studentId){
        return getDayTimeEvent(studentId, getTodayName(), getWeekIsOdd(), LocalTime.now());
    }

    public ScheduleDto getDayTimeEvent(Long studentId, String weekDay, Boolean weekIsOdd, LocalTime time){
        return castOptionalScheduleToDTO(
                scheduleRepository
                        .findByClassTime_StartTimeLessThanEqualAndClassTime_EndTimeGreaterThanEqualAndDayNameAndGroupIdAndWeekIsOdd(
                            time,
                            time,
                            DayOfWeek.valueOf(weekDay.toUpperCase()),
                            studentService.getStudentGroup(studentId).getId(),
                            weekIsOdd
                )
        );
    }

    public ScheduleDto getNextEvent(Long studentId){
        return castOptionalScheduleToDTO(
                scheduleRepository.findByClassTime_StartTimeGreaterThanAndDayNameAndGroupIdAndWeekIsOdd(
                        LocalTime.now(),
                        DayOfWeek.valueOf(getTodayName().toUpperCase()),
                        studentService.getStudentGroup(studentId).getId(),
                        getWeekIsOdd()
                )
        );
    }

    private List<ScheduleDto> castOptionalSchedulesToDTO(Optional<List<Schedule>> schedules){
        return schedules.orElseGet(ArrayList::new)
                .stream()
                .map(ScheduleMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    private ScheduleDto castOptionalScheduleToDTO(Optional<Schedule> schedule){
        return ScheduleMapper.INSTANCE.toDTO(schedule.orElseGet(Schedule::new));
    }

    private String getTodayName(){
        return new SimpleDateFormat("EEEE", Locale.ENGLISH)
                .format(
                        Calendar.getInstance()
                                .getTime()
                                .getTime()
                );
    }

    private static Boolean getWeekIsOdd(){
        final int curYear = 2020;
        LocalDate startDate = LocalDate.of(curYear, 9, 1)
                .with(
                        TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)
                );
        LocalDate curDate = LocalDate.now();
        if(curDate.isBefore(startDate)) return true;
        long diff = ChronoUnit.DAYS.between(startDate, curDate);
        if((diff/7 + 1) % 2 == 0){
            return diff % 7 == 0;
        }
        else return diff % 7 != 0;
    }
}
