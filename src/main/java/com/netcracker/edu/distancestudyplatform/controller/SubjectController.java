package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.dto.wrappers.SubjectDtoList;
import com.netcracker.edu.distancestudyplatform.service.SubjectService;
import com.netcracker.edu.distancestudyplatform.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {
    private final SubjectServiceImpl subjectServiceImpl;
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectServiceImpl subjectServiceImpl, SubjectService subjectService) {
        this.subjectServiceImpl = subjectServiceImpl;
        this.subjectService = subjectService;
    }

    @GetMapping("/allSubjects")
    public SubjectDtoList getAllSubjects(){
        return subjectServiceImpl.getAll();
    }

    @GetMapping("/subject")
    public SubjectDtoList getAllSubjects(@RequestParam(value = "id") Long subjectId){
        return subjectServiceImpl.getAllById(subjectId);
    }

    @GetMapping("/subjectsByTeacher")
    public SubjectDtoList getSubjectsByTeacher(@RequestParam("teacherId") Long teacherId){

        return subjectService.getSubjectsByTeacherId(teacherId);
    }

}
