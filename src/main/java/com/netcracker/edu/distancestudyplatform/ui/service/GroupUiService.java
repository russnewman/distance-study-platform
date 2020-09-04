package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.model.Group;

import java.util.List;

public interface GroupUiService {
    public List<Group> findGroupsByTeacherAndSubject(Long teacherId, String subjectName);
}
