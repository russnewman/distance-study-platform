package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
}
