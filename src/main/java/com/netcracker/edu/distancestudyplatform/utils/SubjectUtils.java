package com.netcracker.edu.distancestudyplatform.utils;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDto;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.mappers.ScheduleMapper;
import com.netcracker.edu.distancestudyplatform.mappers.SubjectMapper;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.model.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectUtils {
    public static List<SubjectDto> castSubjectsToDTO(List<Subject> subjects){
        return subjects
                .stream()
                .map(SubjectMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }
}
