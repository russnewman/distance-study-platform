package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAll();
    Optional<List<Assignment>> findAllByStudent_Id(Long studentId);
    Optional<List<Assignment>> findByEvent_Id(Long eventId);
    Optional<Assignment> findById(Long eventId);
    Optional<List<Assignment>> findAllByStudent_IdAndGradeIsNull(Long studentId);
    Optional<List<Assignment>> findAllByStudent_IdAndGradeIsNotNull(Long studentId);
    Optional<List<Assignment>> findAllByStudent_IdAndEvent_StartDateLessThanEqualAndEvent_EndDateGreaterThanEqual(
            Long studentId, LocalDateTime time1,LocalDateTime time2);
    Optional<List<Assignment>> findByStudent_IdAndEvent_Subject_Id(Long studentId, Long subjectId);
}
