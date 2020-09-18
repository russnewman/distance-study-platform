package com.netcracker.edu.distancestudyplatform.mappers;


import com.netcracker.edu.distancestudyplatform.dto.GroupDto;
import com.netcracker.edu.distancestudyplatform.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);
    GroupDto toDTO(Group group);
}

