package com.netcracker.edu.distancestudyplatform.controller;

import com.netcracker.edu.distancestudyplatform.dto.wrappers.StudentDtoList;
import com.netcracker.edu.distancestudyplatform.model.Student;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    final private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/email")
    public Student getStudentByEmail(@RequestParam String email){
        return studentService.findByEmail(email);
    }

    @GetMapping("/")
    public Student getStudentById(@RequestParam Long studentId){
        return studentService.findById(studentId);
    }



    @GetMapping("/getStudentsByGroup")
    public StudentDtoList getStudentsByGroup(@RequestParam Long groupId){
        return new StudentDtoList(studentService.getStudentsByGroup(groupId));
    }

}
