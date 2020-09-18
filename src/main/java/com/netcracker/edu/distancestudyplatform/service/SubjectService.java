package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.wrappers.SubjectDtoList;
import com.netcracker.edu.distancestudyplatform.model.Subject;

public interface SubjectService {
    SubjectDtoList getAll();
    SubjectDtoList getAllById(Long subjectId);
    Subject findById(Long subjectId);
    Subject findSubjectByName(String name);
    SubjectDtoList getSubjectsByTeacherId(Long teacherId);

}
