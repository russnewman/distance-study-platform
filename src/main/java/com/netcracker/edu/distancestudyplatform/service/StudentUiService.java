package com.netcracker.edu.distancestudyplatform.ui.service;

import com.netcracker.edu.distancestudyplatform.model.Student;

public interface StudentUiService {
    Student getStudentByEmail(String email);
    Student getStudent(Long id);
}
