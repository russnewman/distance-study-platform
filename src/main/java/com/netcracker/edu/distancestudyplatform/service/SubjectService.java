package com.netcracker.edu.distancestudyplatform.service;


import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.dto.wrappers.SubjectDtoList;
import com.netcracker.edu.distancestudyplatform.model.Subject;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getAll();
    List<SubjectDto> getAllById(Long subjectId);
    Subject findById(Long subjectId);
    Subject findSubjectByName(String name);
    List<SubjectDto> getSubjectsByTeacherId(Long teacherId);
    List<Subject> findAll();
}
