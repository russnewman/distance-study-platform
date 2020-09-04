package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.model.Assignment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AssignmentService {
    List<Assignment> getAllAssignments();
    Assignment getAssignment(Long id);
    List<Assignment> getAssignmentByStudent(Long studentId);
    List<Assignment> getAssessedAssignments(Long studentId);
    List<Assignment> getUnassessedAssignments(Long studentId);
}
