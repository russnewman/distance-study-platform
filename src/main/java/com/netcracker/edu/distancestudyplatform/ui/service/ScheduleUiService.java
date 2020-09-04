package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import org.springframework.stereotype.Service;


public interface ScheduleUiService {
    ScheduleDtoList getStudentFullSchedule(Long studentId);
    ScheduleDtoList getStudentTodaySchedule(Long studentId);
    ScheduleDtoList getStudentTomorrowSchedule(Long studentId);
    SubjectDto getStudentCurrentSubject(Long studentId);
    SubjectDto getStudentNextSubject(Long studentId);
    ScheduleDtoList getStudentSubjectSchedule(Long studentId, Long subjectId);
}
