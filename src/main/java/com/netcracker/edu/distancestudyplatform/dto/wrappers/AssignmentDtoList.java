package com.netcracker.edu.distancestudyplatform.dto.wrappers;

import com.netcracker.edu.distancestudyplatform.dto.assignment.AssignmentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AssignmentDtoList {
    private List<AssignmentDto> assignments;
}