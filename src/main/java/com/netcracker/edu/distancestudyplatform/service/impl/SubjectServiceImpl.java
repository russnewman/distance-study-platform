package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.SubjectDtoList;
import com.netcracker.edu.distancestudyplatform.repository.SubjectRepository;
import com.netcracker.edu.distancestudyplatform.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.netcracker.edu.distancestudyplatform.utils.SubjectUtils.castSubjectsToDTO;


@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
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
}
