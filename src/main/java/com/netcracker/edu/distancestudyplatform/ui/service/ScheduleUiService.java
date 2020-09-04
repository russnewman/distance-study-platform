package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ScheduleUiService {
    ScheduleDtoList getStudentFullSchedule(Long studentId);


    List<Schedule> getTeacherSchedule(Long teacherId);
    List<Schedule> getTomorrowTeacherSchedule(Long teacherId);

    List<Schedule> getTeacherSchedule(Long teacherId, Boolean weekIsOdd);
    List<Schedule> getTomorrowTeacherSchedule(Long teacherId, Boolean weekIsOdd);

    List<Schedule> getSubjectTeacherSchedule(List<Schedule> list, Long subjectId);
    Map<Schedule, List<Group>> mapScheduleToGroups(List<Schedule> list);
    List<Schedule> mapKeysList(Map<Schedule, List<Group>> map);

    ScheduleDtoList getStudentTodaySchedule(Long studentId);
    ScheduleDtoList getStudentTomorrowSchedule(Long studentId);
    SubjectDto getStudentCurrentSubject(Long studentId);
    SubjectDto getStudentNextSubject(Long studentId);
    ScheduleDtoList getStudentSubjectSchedule(Long studentId, Long subjectId);
}
