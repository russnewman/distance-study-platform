package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.dto.assignment.AssignmentDto;

import java.util.List;

public interface AssignmentUiService {
    List<AssignmentDto> getAllStudentAssignments(Long studentId);
    List<AssignmentDto> getAllStudentSubjectAssignments(Long studentId, Long subjectId);
    List<AssignmentDto> getAssessedStudentAssignments(Long studentId);
    List<AssignmentDto> getUnassessedStudentAssignments(Long studentId);
    List<AssignmentDto> getActiveStudentAssignments(Long studentId);
    List<AssignmentDto> getEventStudentAssignments(Long studentId, Long eventId);
    List<AssignmentDto> getEventStudentAssessedAssignments(Long studentId, Long eventId);
    List<AssignmentDto> getEventStudentUnassessedAssignments(Long studentId, Long eventId);
    void save(AssignmentDto assignment);
}
