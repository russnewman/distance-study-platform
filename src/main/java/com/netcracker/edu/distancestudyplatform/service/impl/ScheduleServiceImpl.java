package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.dto.ScheduleVDto;
import com.netcracker.edu.distancestudyplatform.mappers.ScheduleMapper;
import com.netcracker.edu.distancestudyplatform.mappers.ScheduleVMapper;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.repository.ScheduleRepository;
import com.netcracker.edu.distancestudyplatform.service.ScheduleService;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import com.netcracker.edu.distancestudyplatform.utils.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
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

    @Override
    public List<ScheduleVDto> getGroupSchedule(Group studentGroup) {
        return ScheduleVMapper.INSTANCE.map(
                scheduleRepository.findByGroupId(studentGroup.getId())
                .orElseGet(ArrayList::new));
    }

    @Override
    public List<ScheduleVDto> getStudentSchedule(Long studentId) {
        return getGroupSchedule(
                studentService.getStudentGroup(studentId)
        );
    }

    @Override
    public List<ScheduleVDto> getAnyDaySchedule(Long studentId, String weekDay, Boolean weekIsOdd){
        return ScheduleVMapper.INSTANCE.map(
                scheduleRepository.findByDayNameAndGroupIdAndWeekIsOdd(
                    DayOfWeek.valueOf(weekDay.toUpperCase()),
                    studentService.getStudentGroup(studentId).getId(),
                    weekIsOdd
                )
                .orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<ScheduleVDto> getAnyDaySchedule(Long studentId, String weekDay){
        return ScheduleVMapper.INSTANCE.map(
                scheduleRepository.findByDayNameAndGroupId(
                    DayOfWeek.valueOf(weekDay.toUpperCase()),
                    studentService.getStudentGroup(studentId).getId()
                )
                .orElseGet(ArrayList::new)
        );
    }

    @Override
    public List<ScheduleVDto> getNextDaySchedule(Long studentId){
        return getAnyDaySchedule(studentId, ScheduleUtils.getNextDayName(), ScheduleUtils.getWeekIsOdd());
    }

    @Override
    public List<ScheduleVDto> getTodaySchedule(Long studentId){
        return getAnyDaySchedule(studentId, ScheduleUtils.getTodayName(), ScheduleUtils.getWeekIsOdd());
    }

    @Override
    public ScheduleVDto getCurrentEvent(Long studentId){
        return getDayTimeEvent(studentId, ScheduleUtils.getTodayName(), ScheduleUtils.getWeekIsOdd(), LocalTime.now());
    }
    @Override
    public List<ScheduleVDto> getSubjectStudentSchedule(Long studentId, Long subjectId){
        return ScheduleVMapper.INSTANCE.map(
                scheduleRepository.findBySubject_IdAndGroup_Id(
                        subjectId, studentService.getStudentGroup(studentId).getId()
                )
                        .orElseGet(ArrayList::new)
        );
    }

    @Override
    public ScheduleVDto getDayTimeEvent(Long studentId, String weekDay, Boolean weekIsOdd, LocalTime time){
        return ScheduleVMapper.INSTANCE.toDTO(
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

    @Override
    public ScheduleVDto getNextEvent(Long studentId){
        return ScheduleVMapper.INSTANCE.toDTO(
                scheduleRepository.findFirstByClassTime_StartTimeGreaterThanAndDayNameAndGroupIdAndWeekIsOdd(
                        LocalTime.now(),
                        DayOfWeek.valueOf(ScheduleUtils.getTodayName().toUpperCase()),
                        studentService.getStudentGroup(studentId).getId(),
                        ScheduleUtils.getWeekIsOdd()
                ).orElseGet(Schedule::new)
        );
    }



    //----------------------------------------------------------//
    //Methods need for teacherTT functionality//


    @Override
    public List<ScheduleDto> getTeacherSchedule(Long teacherId, Optional<Boolean> weekIsOddOptional) {
        List<Schedule> schedules;
        if (weekIsOddOptional.isPresent()) {
            schedules = scheduleRepository.findAllByTeacherIdAndWeekIsOdd(teacherId, weekIsOddOptional.get()).orElseGet(ArrayList::new);
        }
        else schedules = scheduleRepository.findAllByTeacherId(teacherId).orElseGet(ArrayList::new);
        return ScheduleUtils.castSchedulesToDTO(schedules);
    }


    @Override
    public List<ScheduleDto> getTomorrowTeacherSchedule(Long teacherId, Optional<Boolean> weekIsOddOptional) {

        List<Schedule> schedules;
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(ScheduleUtils.getTomorrowName().toUpperCase());
        if (weekIsOddOptional.isPresent()){
            schedules = scheduleRepository.findAllByTeacherIdAndWeekIsOddAndDayName(teacherId, weekIsOddOptional.get(), dayOfWeek).orElseGet(ArrayList::new);
        }
        else schedules = scheduleRepository.findAllByTeacherIdAndDayName(teacherId, dayOfWeek).orElseGet(ArrayList::new);
        return ScheduleUtils.castSchedulesToDTO(schedules);
    }


    @Override
    public List<ScheduleDto> getSubjectTeacherSchedule(List<ScheduleDto> list, Long subjectId) {
        return  list
                .stream()
                .filter(x -> x.getSubjectDto().getId().equals(subjectId))
                .collect(Collectors.toList());
    }


    public List<ScheduleDto> getSubjectTeacherSchedule(Long teacherId, String subjectName) {
        return ScheduleUtils.castSchedulesToDTO(scheduleRepository.findAllByTeacherIdAndSubjectName(teacherId, subjectName).orElseGet(ArrayList::new));
    }



    @Override
    public void updateLessonLink(Long scheduleId, String link) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
        schedule.setLessonLink(link);
        scheduleRepository.save(schedule);
    }

}
