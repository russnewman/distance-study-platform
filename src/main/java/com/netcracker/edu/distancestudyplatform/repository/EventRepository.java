package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<List<Event>> findBySubject_IdAndGroup_Id(Long subjectId, Long groupId);
    Optional<List<Event>> findByGroup_Id(Long groupId);
    Optional<List<Event>> findByGroup_IdAndEndDateGreaterThan(Long groupId, LocalDateTime endTime);
    Optional<List<Event>> findByGroup_IdAndSubject_IdAndEndDateGreaterThan(Long groupId, Long subjectId, LocalDateTime endTime);
    Optional<List<Event>> findAllByTeacher(Teacher teacher);
    Optional<List<Event>> findAllByTeacherAndSubject(Teacher teacher, Subject subject);
}
