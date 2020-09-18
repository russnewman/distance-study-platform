package com.netcracker.edu.distancestudyplatform.service.impl;

import com.netcracker.edu.distancestudyplatform.dto.ChangePasswordRequest;
import com.netcracker.edu.distancestudyplatform.dto.UserDto;
import com.netcracker.edu.distancestudyplatform.exception.DifferentPasswordsException;
import com.netcracker.edu.distancestudyplatform.exception.UserNotFoundException;
import com.netcracker.edu.distancestudyplatform.mappers.UserMapper;
import com.netcracker.edu.distancestudyplatform.model.Student;
import com.netcracker.edu.distancestudyplatform.model.Teacher;
import com.netcracker.edu.distancestudyplatform.model.User;
import com.netcracker.edu.distancestudyplatform.service.StudentService;
import com.netcracker.edu.distancestudyplatform.service.TeacherService;
import com.netcracker.edu.distancestudyplatform.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private @Getter StudentService studentService;
    private @Getter TeacherService teacherService;
    private @Getter UserMapper userMapper;

    public UserServiceImpl(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.userMapper = UserMapper.INSTANCE;
    }

    public User findByEmail(String email) {
        Student student = getStudentService().findByEmail(email);
        Teacher teacher = getTeacherService().findByEmail(email);

        if (student != null && teacher != null) {
            throw new IllegalStateException("Student and Teacher can't have the same email address");
        }

        if (teacher == null && student == null) {
            log.trace("No such student and teacher for email: " + email);
            throw new UserNotFoundException("No such student and teacher", email);
        }

        return teacher != null ? teacher : student;
    }

    @Override
    public UserDto getInfoByEmail(String email) {
        User user = findByEmail(email);
        return getUserMapper().toDto(user);
    }

    @Override
    public User changePassword(String email, ChangePasswordRequest request) throws DifferentPasswordsException {
        User user = findByEmail(email);
        if (!user.getPassword().equals(request.getOldPassword())) {
            log.trace("Incorrect password was entered for user: " + user.getEmail());
            throw new DifferentPasswordsException();
        }
        if (user.getClass().equals(Student.class)) {
            Student s = (Student) user;
            s.setPassword(request.getNewPassword());
            getStudentService().save(s);
        } else if (user.getClass().equals(Teacher.class)) {
            Teacher t = (Teacher) user;
            t.setPassword(request.getNewPassword());
            getTeacherService().save(t);
        } else {
            throw new UnsupportedOperationException("Can't change password for user with class: " + user.getClass());
        }
        log.trace("Password has been changed for user: " + user.getEmail());
        return user;
    }
}
