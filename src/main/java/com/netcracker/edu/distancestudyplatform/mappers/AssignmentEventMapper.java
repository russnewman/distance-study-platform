package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.assignment.AssignmentEventDto;
import com.netcracker.edu.distancestudyplatform.model.Assignment;
import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AssignmentEventMapper {
    AssignmentEventMapper INSTANCE = Mappers.getMapper(AssignmentEventMapper.class);

    Assignment toAssignment(AssignmentEventDto assignmentDto);

    @Mapping(target = "fileId", ignore = true)
    AssignmentEventDto toDTO(Assignment assignment);

    List<AssignmentEventDto> map(List<Assignment> assignments);

    @AfterMapping
    default void setFileId(@MappingTarget AssignmentEventDto assignmentDto, Assignment assignment) {
        DatabaseFile file = assignment.getDbFile();
        if (file != null) assignmentDto.setFileId(file.getId());
    }
}
