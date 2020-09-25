package com.netcracker.edu.distancestudyplatform.service;


import com.netcracker.edu.distancestudyplatform.dto.StudentDto;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Student;

import java.util.List;

public interface StudentService {
    Student findByEmail(String email);
    Student findById(Long id);
    Group getStudentGroup(Long userId);
    Student save(Student s);
    List<StudentDto> getStudentsByGroup(Long groupId);
}
