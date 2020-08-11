package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.ClassTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassTimeRepository extends JpaRepository<ClassTime, Long> {
}
