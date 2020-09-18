package com.netcracker.edu.distancestudyplatform.dto.wrappers;

import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SubjectDtoList {
    List<SubjectDto> subjects;
}
