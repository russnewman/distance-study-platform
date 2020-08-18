package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<List<Schedule>> findByGroupId(Long groupId);
    Optional<List<Schedule>> findByDayNameAndGroupIdAndWeekIsOdd(String dayName, Long groupId, boolean weekIsOdd);
    Optional<List<Schedule>> findByDayNameAndGroupId(String dayName, Long groupId);
    Optional<Schedule> findByClassTime_StartTimeLessThanEqualAndClassTime_EndTimeGreaterThanEqualAndDayNameAndGroupIdAndWeekIsOdd(
            LocalTime time1, LocalTime time2, String dayName, Long groupId, Boolean weekIsOdd
    );
}
