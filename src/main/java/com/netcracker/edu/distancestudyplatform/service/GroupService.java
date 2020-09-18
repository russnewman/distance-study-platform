package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.GroupDto;
import com.netcracker.edu.distancestudyplatform.model.Group;

import java.util.List;


public interface GroupService {
     Group findById(Long id);
     Group findGroupByGroupName(String groupName);
     List<GroupDto> findGroupsByTeacherAndSubject(Long teacherId, String subjectName);
}
