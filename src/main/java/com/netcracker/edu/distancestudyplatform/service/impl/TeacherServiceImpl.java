package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.model.Teacher;
import com.netcracker.edu.distancestudyplatform.repository.TeacherRepository;
import com.netcracker.edu.distancestudyplatform.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepo;

    public TeacherServiceImpl(TeacherRepository teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @Override
    public @Nullable Teacher findByEmail(String email) {
        return teacherRepo.findByEmail(email).orElse(null);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepo.findAll();
    }
}
