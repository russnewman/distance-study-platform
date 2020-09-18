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
    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public @Nullable Teacher findByEmail(String email) {
        return teacherRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Teacher findById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseGet(() -> new Teacher());
    }

    @Override
    public Teacher save(Teacher t) {
        return teacherRepository.save(t);
    }


}
