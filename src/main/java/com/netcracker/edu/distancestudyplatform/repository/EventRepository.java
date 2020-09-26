package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import com.netcracker.edu.distancestudyplatform.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<List<Event>> findBySubject_IdAndGroup_IdOrderByStartDate(Long subjectId, Long groupId);
    Optional<List<Event>> findByGroup_IdOrderByStartDate(Long groupId);
    Optional<List<Event>> findByGroup_IdAndEndDateGreaterThanOrderByStartDate(Long groupId, LocalDateTime endTime);
    Optional<List<Event>> findByGroup_IdAndSubject_IdAndEndDateGreaterThanOrderByStartDate(Long groupId, Long subjectId, LocalDateTime endTime);
    Optional<List<Event>> findAllByTeacherOrderByStartDate(Teacher teacher);
    Optional<List<Event>> findAllByTeacherAndSubjectOrderByStartDate(Teacher teacher, Subject subject);
}
