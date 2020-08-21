package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Student;
import com.netcracker.edu.distancestudyplatform.repository.StudentRepository;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentServiceIml implements StudentService {
    private StudentRepository studentRepo;

    public StudentServiceIml(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public @Nullable Student findByEmail(String email) {
        return studentRepo.findByEmail(email).orElse(null);
    }

    @Override
    public @Nullable Student findById(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    @Override
    public Group getStudentGroup(Long userId) {
        return findById(userId).getGroup();
    }


}
