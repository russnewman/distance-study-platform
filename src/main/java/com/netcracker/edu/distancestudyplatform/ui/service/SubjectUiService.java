package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.dto.SubjectDtoList;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SubjectUiService {
    SubjectDtoList getAllSubjects();
    public List<Subject> getSubjectsByTeacherId(Long teacherId);

}
