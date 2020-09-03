package com.netcracker.edu.distancestudyplatform.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Schedule.class)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "class_time_id")
    private ClassTime classTime;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "class_type")
    @Enumerated(EnumType.STRING)
    private ClassType classType;


    @Column(name = "day_name")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayName;

//
//    @Column(name = "day_name")
//    private String dayName;

    @Column(name = "odd_week")
    private Boolean weekIsOdd;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return classTime.getStartTime().equals(schedule.classTime.getStartTime()) &&
                dayName.equals(schedule.dayName) &&
                weekIsOdd.equals(schedule.weekIsOdd)&&
                subject.equals(schedule.subject);
    }


    @Override
    public int hashCode() {
        return Objects.hash(classTime.getStartTime(), dayName, weekIsOdd);
    }

}
