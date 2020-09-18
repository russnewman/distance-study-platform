package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.dto.AssignmentDto;
import java.util.List;


public interface AssignmentService {
    List<AssignmentDto> getAllAssignments();
    AssignmentDto getAssignment(Long id);
    List<AssignmentDto> getAssignmentByStudent(Long studentId);
    List<AssignmentDto> getAssessedAssignments(Long studentId);
    List<AssignmentDto> getUnassessedAssignments(Long studentId);
    List<AssignmentDto> getActiveAssignments(Long studentId);
    List<AssignmentDto> getSubjectAssignments(Long studentId, Long subjectId);
    List<AssignmentDto> getEventAssignments(Long studentId, Long eventId);
    List<AssignmentDto> getEventAssessedAssignments(Long studentId, Long eventId);
    List<AssignmentDto> getEventUnassessedAssignments(Long studentId, Long eventId);
//    void saveAssignment(AssignmentDto assignmentDto);
    void update(AssignmentDto assignment);

    List<AssignmentDto> getAssignmentsByEvent(Long eventId);
}
