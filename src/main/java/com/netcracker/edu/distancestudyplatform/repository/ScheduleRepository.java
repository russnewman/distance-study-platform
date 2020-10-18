package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.ClassTime;
import com.netcracker.edu.distancestudyplatform.model.Schedule;
import com.netcracker.edu.distancestudyplatform.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<List<Schedule>> findByGroupId(Long groupId);
    Optional<List<Schedule>> findByDayNameAndGroupIdAndWeekIsOdd(DayOfWeek weekDay, Long groupId, boolean weekIsOdd);
    Optional<List<Schedule>> findByDayNameAndGroupId(DayOfWeek weekDay, Long groupId);
    Optional<Schedule> findFirstByClassTime_StartTimeLessThanEqualAndClassTime_EndTimeGreaterThanEqualAndDayNameAndGroupIdAndWeekIsOdd(
            LocalTime time1, LocalTime time2, DayOfWeek weekDay, Long groupId, Boolean weekIsOdd
    );
    Optional<Schedule> findFirstByClassTime_StartTimeGreaterThanAndDayNameAndGroupIdAndWeekIsOdd(
            LocalTime time, DayOfWeek weekDay, Long groupId, Boolean weekIsOdd
    );
    Optional<List<Schedule>> findBySubject_IdAndGroup_Id(Long subjectId, Long groupId);
    Optional<List<Schedule>> findAllByTeacherId(Long teacherId);
    Optional<List<Schedule>> findAllByTeacherIdAndDayName(Long teacherId, DayOfWeek dayName);
    Optional<List<Schedule>> findAllByTeacherIdAndWeekIsOdd(Long teacherId, Boolean weekIsOdd);
    Optional<List<Schedule>> findAllByTeacherIdAndWeekIsOddAndDayName(
            Long teacherId, Boolean weekIsOdd, DayOfWeek dayName
    );
    Optional<List<Schedule>> findAllByTeacherIdAndSubjectName(Long TeacherId, String subjectName);
    Optional<List<Schedule>> findAllByTeacherIdAndWeekIsOddAndDayNameAndClassTime(Long teacher_id, Boolean weekIsOdd, DayOfWeek dayName, ClassTime classTime);
}
