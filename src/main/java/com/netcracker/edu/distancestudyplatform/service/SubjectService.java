package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.dto.SubjectDtoList;

import java.util.List;

public interface SubjectService {
    SubjectDtoList getAll();
    SubjectDtoList getAllById(Long subjectId);
}
