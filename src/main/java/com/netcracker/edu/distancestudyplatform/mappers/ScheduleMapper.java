package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDTO;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "subjectDTO", target = "subject")
    @Mapping(source = "classTimeDTO", target = "classTime")
    Schedule toSchedule(ScheduleDTO scheduleDTO);

    @InheritInverseConfiguration
    ScheduleDTO toDTO(Schedule schedule);
}
