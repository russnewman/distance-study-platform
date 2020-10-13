package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.Assignment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAll();
    Optional<List<Assignment>> findAllByStudent_Id(Long studentId);
    Optional<Assignment> findById(Long eventId);
    Optional<List<Assignment>> findAllByStudent_IdAndGradeIsNull(Long studentId);
    Optional<List<Assignment>> findAllByStudent_IdAndGradeIsNotNull(Long studentId);
    Optional<List<Assignment>> findAllByStudent_IdAndEvent_StartDateLessThanEqualAndEvent_EndDateGreaterThanEqual(
            Long studentId, LocalDateTime time1,LocalDateTime time2);
    Optional<List<Assignment>> findByStudent_IdAndEvent_Subject_Id(Long studentId, Long subjectId);
    Optional<List<Assignment>> findByStudent_IdAndEvent_Id(Long studentId, Long eventId);
    Optional<List<Assignment>> findByStudent_IdAndEvent_IdAndGradeIsNull(
            Long studentId, Long eventId
    );
    Optional<List<Assignment>> findByStudent_IdAndEvent_IdAndGradeIsNotNull(
            Long studentId, Long eventId
    );
    Optional<Assignment> findAssignmentById(Long id);
    Optional<List<Assignment>> findAllByEvent_Id(Long eventId);
    Optional<List<Assignment>> findByEvent_IdAndStudent_Id(Long studentId, Long eventId, Pageable pageable);
}
