package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.AssignmentEventDto;
import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.model.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventStudentDtoMapper {
    EventStudentDtoMapper INSTANCE = Mappers.getMapper(EventStudentDtoMapper.class);

    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "fileId", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    EventStudentDto toDTO(Event event);

    List<EventStudentDto> map(List<Event> events);

    @AfterMapping
    default void setTeacherName(@MappingTarget EventStudentDto eventStudentDto, Event event){
        Teacher teacher = event.getTeacher();
        eventStudentDto.setTeacher(teacher.getName() + " " + teacher.getSurname());
    }

    @AfterMapping
    default void setSubjectName(@MappingTarget EventStudentDto eventStudentDto, Event event){
        Subject subject = event.getSubject();
        eventStudentDto.setSubject(subject.getName());
    }

    @AfterMapping
    default void setFileId(@MappingTarget EventStudentDto eventStudentDto, Event event){
        DatabaseFile file = event.getDbFile();
        if (file != null) {
            eventStudentDto.setFileId(file.getId());
        }
    }

    @AfterMapping
    default void setAssignments(@MappingTarget EventStudentDto eventDto, Event event){
        List<Assignment> assignments = event.getAssignments();
        eventDto.setAssignments(
                AssignmentEventMapper.INSTANCE.map(assignments)
        );
    }
}
