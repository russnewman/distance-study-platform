package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.wrappers.SubjectDtoList;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import com.netcracker.edu.distancestudyplatform.repository.SubjectRepository;
import com.netcracker.edu.distancestudyplatform.service.SubjectService;
import com.netcracker.edu.distancestudyplatform.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.netcracker.edu.distancestudyplatform.utils.SubjectUtils.castSubjectsToDTO;


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
    public SubjectDtoList getAll() {
        return new SubjectDtoList(castSubjectsToDTO(
               subjectRepository.findAll()
        ));
    }

    @Override
    public SubjectDtoList getAllById(Long subjectId) {
        return new SubjectDtoList(castSubjectsToDTO(
                subjectRepository.findAllById(subjectId)
        ));
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
    public SubjectDtoList getSubjectsByTeacherId(Long teacherId) {
        List<Subject> subjects = teacherService.findById(teacherId).getSubjects();
        return new SubjectDtoList(castSubjectsToDTO(teacherService.findById(teacherId).getSubjects()));
    }
}
