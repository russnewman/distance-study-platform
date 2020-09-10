package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.model.Event;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
    @InheritInverseConfiguration
    EventStudentDto toDTO(Event event);
    @Mapping(source = "teacherDto", target = "teacher")
    @Mapping(source = "subjectDto", target = "subject")
    Event toEvent(EventStudentDto eventStudentDto);
    List<EventStudentDto> map(List<Event> events);
}
