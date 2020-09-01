package com.netcracker.edu.distancestudyplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "odd_week")
    private Boolean weekIsOdd;
}
