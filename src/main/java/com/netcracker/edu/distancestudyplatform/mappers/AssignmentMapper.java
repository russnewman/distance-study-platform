package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.model.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AssignmentMapper {
    AssignmentMapper INSTANCE = Mappers.getMapper(AssignmentMapper.class);
    Assignment toAssignment(AssignmentDto assignmentDto);
    AssignmentDto toDTO(Assignment assignment);
    List<AssignmentDto> map(List<Assignment> assignments);
}
