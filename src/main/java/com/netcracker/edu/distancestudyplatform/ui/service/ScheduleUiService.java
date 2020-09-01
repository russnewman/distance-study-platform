package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.dto.ScheduleDtoList;
import org.springframework.stereotype.Service;


public interface ScheduleUiService {
    ScheduleDtoList getStudentFullSchedule(Long studentId);
}
