package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.dto.SubjectDtoList;
import com.netcracker.edu.distancestudyplatform.service.impl.ScheduleServiceImpl;
import com.netcracker.edu.distancestudyplatform.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {
    private final SubjectServiceImpl subjectServiceImpl;

    @Autowired
    public SubjectController(SubjectServiceImpl subjectServiceImpl) {
        this.subjectServiceImpl = subjectServiceImpl;
    }

    @GetMapping("/allSubjects")
    public SubjectDtoList getAllSubjects(){
        return subjectServiceImpl.getAll();
    }

    @GetMapping("/subject")
    public SubjectDtoList getAllSubjects(@RequestParam(value = "id") Long subjectId){
        return subjectServiceImpl.getAllById(subjectId);
    }

}
