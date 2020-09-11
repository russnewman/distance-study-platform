package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDtoList;
import com.netcracker.edu.distancestudyplatform.model.Subject;

import java.util.List;

public interface SubjectService {
    SubjectDtoList getAll();
    SubjectDtoList getAllById(Long subjectId);
    Subject findById(Long subjectId);
    Subject findSubjectByName(String name);
    SubjectDtoList getSubjectsByTeacherId(Long teacherId);

}
