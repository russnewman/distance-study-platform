package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.AssignmentDto;
import com.netcracker.edu.distancestudyplatform.model.Assignment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AssignmentService {
    List<AssignmentDto> getAllAssignments();
    AssignmentDto getAssignment(Long id);
    List<AssignmentDto> getAssignmentByStudent(Long studentId);
    List<AssignmentDto> getAssessedAssignments(Long studentId);
    List<AssignmentDto> getUnassessedAssignments(Long studentId);
    List<AssignmentDto> getActiveAssignments(Long studentId);
    List<AssignmentDto> getSubjectAssignments(Long studentId, Long subjectId);
}
