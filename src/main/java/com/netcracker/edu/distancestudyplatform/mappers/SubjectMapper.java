package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.StudentDto;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);
    SubjectDto toDTO(Subject subject);
}
