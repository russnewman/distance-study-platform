package com.netcracker.edu.distancestudyplatform.services;

import com.netcracker.edu.distancestudyplatform.model.Student;
import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Group getStudentGroup(Long userId) {
        return getStudent(userId).getGroup();
    }

    public Student getStudent(Long userId) {
        return studentRepository.findById(userId).orElseGet(() -> new Student());
    }
}
