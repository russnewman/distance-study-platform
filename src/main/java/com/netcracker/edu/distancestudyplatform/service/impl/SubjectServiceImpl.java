package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.SubjectDto;
import com.netcracker.edu.distancestudyplatform.mappers.SubjectMapper;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import com.netcracker.edu.distancestudyplatform.repository.SubjectRepository;
import com.netcracker.edu.distancestudyplatform.service.SubjectService;
import com.netcracker.edu.distancestudyplatform.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final TeacherService teacherService;


    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, TeacherService teacherService) {
        this.subjectRepository = subjectRepository;
        this.teacherService = teacherService;
    }


    @Override
    public List<SubjectDto> getAll() {
        return SubjectMapper.INSTANCE.map(subjectRepository.findAll());
    }

    @Override
    public List<SubjectDto> getAllById(Long subjectId) {
        return SubjectMapper.INSTANCE.map(
                subjectRepository.findAllById(subjectId)
        );
    }

    @Override
    public Subject findById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseGet(Subject::new);
    }

    @Override
    public Subject findSubjectByName(String name) {
        return subjectRepository.findSubjectByName(name);
    }


    @Override
    public List<SubjectDto> getSubjectsByTeacherId(Long teacherId) {
        return SubjectMapper.INSTANCE.map(teacherService.findById(teacherId).getSubjects());
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
}
