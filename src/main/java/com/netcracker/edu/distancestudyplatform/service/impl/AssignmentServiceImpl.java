package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.model.Assignment;
import com.netcracker.edu.distancestudyplatform.repository.AssignmentRepository;
import com.netcracker.edu.distancestudyplatform.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository){
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment getAssignment(Long id) {
        return assignmentRepository.findById(id).orElseGet(Assignment::new);
    }

    @Override
    public List<Assignment> getAssignmentByStudent(Long studentId) {
        return assignmentRepository.findAllByStudent_Id(studentId);
    }

    @Override
    public List<Assignment> getAssessedAssignments(Long studentId) {
        return assignmentRepository.findAllByStudent_IdAndGradeIsNotNull(studentId);
    }

    @Override
    public List<Assignment> getUnassessedAssignments(Long studentId) {
        return assignmentRepository.findAllByStudent_IdAndGradeIsNull(studentId);
    }
}
