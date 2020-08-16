package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.model.Student;
import com.netcracker.edu.distancestudyplatform.model.Teacher;
import com.netcracker.edu.distancestudyplatform.model.User;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import com.netcracker.edu.distancestudyplatform.service.TeacherService;
import com.netcracker.edu.distancestudyplatform.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private @Getter StudentService studentRepo;
    private @Getter TeacherService teacherRepo;

    public UserServiceImpl(StudentService studentRepo, TeacherService teacherRepo) {
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    public User findByEmail(String email) {
        Student student = getStudentRepo().findByEmail(email);
        Teacher teacher = getTeacherRepo().findByEmail(email);

        if (student != null && teacher != null) {
            throw new IllegalStateException("Student and Teacher can't have the same email address");
        }

        if (teacher == null && student == null) {

            log.trace("No such student and teacher for email: " + email);
            throw new EntityNotFoundException("No such student and teacher");
        }

        return teacher != null ? teacher : student;
    }
}
