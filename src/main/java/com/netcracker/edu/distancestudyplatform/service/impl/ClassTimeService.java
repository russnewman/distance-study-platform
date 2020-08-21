package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.model.ClassTime;
import com.netcracker.edu.distancestudyplatform.repository.ClassTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ClassTimeService {
    private final
    ClassTimeRepository classTimeRepository;

    @Autowired
    public ClassTimeService(ClassTimeRepository classTimeRepository) {
        this.classTimeRepository = classTimeRepository;
    }

    public LocalTime getStartTime(Long id) {
        return classTimeRepository.findById(id).orElseGet(ClassTime::new).getStartTime();
    }

    public List<ClassTime> getAll() {
        return classTimeRepository.findAll();
    }

}
