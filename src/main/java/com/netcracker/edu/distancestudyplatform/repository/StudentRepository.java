package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.Group;
import com.netcracker.edu.distancestudyplatform.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends UserRepository<Student> {
    Optional<Student> findByEmail(String email);
    Optional<List<Student>> findAllByGroup_Id(Long groupId);
}
