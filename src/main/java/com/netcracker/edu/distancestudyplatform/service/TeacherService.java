package com.netcracker.edu.distancestudyplatform.service;

import com.netcracker.edu.distancestudyplatform.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher findByEmail(String email);
    Teacher findById(Long teacherId);
    Teacher save(Teacher t);
}
