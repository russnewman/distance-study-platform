package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.model.Student;

public interface StudentService {
    Student findByEmail(String email);
    Student findById(Long id);
}
