package com.netcracker.edu.distancestudyplatform.utils;

import com.netcracker.edu.distancestudyplatform.dto.assignment.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.mappers.AssignmentMapper;
import com.netcracker.edu.distancestudyplatform.model.Assignment;

import java.util.List;
import java.util.stream.Collectors;

public class AssignmentUtils {
    public static List<AssignmentDto> castAssignmentsToDto(List<Assignment> assignments){
        return assignments
                .stream()
                .map(AssignmentMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}
