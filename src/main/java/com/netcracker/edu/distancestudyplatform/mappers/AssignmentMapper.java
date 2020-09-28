package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.dto.EventStudentDto;
import com.netcracker.edu.distancestudyplatform.model.Assignment;
import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import com.netcracker.edu.distancestudyplatform.model.Event;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AssignmentMapper {
    AssignmentMapper INSTANCE = Mappers.getMapper(AssignmentMapper.class);

    Assignment toAssignment(AssignmentDto assignmentDto);


    AssignmentDto toDTO(Assignment assignment);

    List<AssignmentDto> map(List<Assignment> assignments);

    @AfterMapping
    default void setFileId(@MappingTarget Assignment assignment, AssignmentDto assignmentDto){
        assignment.setDbFile(DatabaseFileMapper.INSTANCE.toDbFile(assignmentDto.getDbFile()));
    }
}
