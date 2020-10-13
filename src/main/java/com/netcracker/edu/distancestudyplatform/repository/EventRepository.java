package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.Event;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import com.netcracker.edu.distancestudyplatform.model.Teacher;
import com.netcracker.edu.distancestudyplatform.service.RestPageImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Page<Event>> findBySubject_IdAndGroup_Id(Long subjectId, Long groupId, Pageable pageable);
    Optional<Page<Event>> findByGroup_Id(Long groupId, Pageable pageable);
    Optional<List<Event>> findByGroup_IdAndEndDateGreaterThanOrderByStartDate(Long groupId, LocalDateTime endTime);
    Optional<List<Event>> findByGroup_IdAndSubject_IdAndEndDateGreaterThanOrderByStartDate(Long groupId, Long subjectId, LocalDateTime endTime);

    Page<Event> findAllByTeacher(Teacher teacher, Pageable pageable);
    Page<Event> findAllByTeacherAndSubject(Teacher teacher, Subject subject, Pageable pageable);

}
