package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleVDto;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import com.netcracker.edu.distancestudyplatform.model.Teacher;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ScheduleVMapper {
    ScheduleVMapper INSTANCE = Mappers.getMapper(ScheduleVMapper.class);

    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(source = "classTime", target = "classTimeDto")
    ScheduleVDto toDTO(Schedule schedule);

    List<ScheduleVDto> map(List<Schedule> schedules);

    @AfterMapping
    default void setTeacherName(@MappingTarget ScheduleVDto scheduleVDto, Schedule schedule){
        Teacher teacher = schedule.getTeacher();
        scheduleVDto.setTeacher(teacher.getName() + " " + teacher.getSurname());
    }

    @AfterMapping
    default void setSubjectName(@MappingTarget ScheduleVDto scheduleVDto, Schedule schedule){
        Subject s = schedule.getSubject();
        scheduleVDto.setSubject(s.getName());
    }
}
