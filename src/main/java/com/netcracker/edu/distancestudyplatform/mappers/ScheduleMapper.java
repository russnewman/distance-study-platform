package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleMapper {
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "subjectDto", target = "subject")
    @Mapping(source = "classTimeDto", target = "classTime")
    Schedule toSchedule(ScheduleDto scheduleDto);

    @InheritInverseConfiguration
    ScheduleDto toDTO(Schedule schedule);
}
