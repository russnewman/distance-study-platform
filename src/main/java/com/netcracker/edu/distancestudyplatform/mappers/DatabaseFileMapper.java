package com.netcracker.edu.distancestudyplatform.mappers;

import com.netcracker.edu.distancestudyplatform.dto.DatabaseFileDto;
import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.model.DatabaseFile;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DatabaseFileMapper {
    DatabaseFileMapper INSTANCE = Mappers.getMapper(DatabaseFileMapper.class);
    DatabaseFile toDbFile(DatabaseFileDto dbFileDto);
    DatabaseFileDto toDTO(DatabaseFile dbFile);
    List<DatabaseFileDto> map(List<DatabaseFile> dbFiles);
}
