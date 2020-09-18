package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.EventDto;
import com.netcracker.edu.distancestudyplatform.model.Event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "dbFile", target = "databaseFileDto")
    @Mapping(source = "teacher", target = "teacher")
    @Mapping(source = "subject", target = "subject")
    @Mapping(source = "group", target = "group")
    EventDto toDTO(Event event);
}
