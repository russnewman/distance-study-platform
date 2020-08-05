package com.netcracker.edu.distancestudyplatform.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "class_time")
@Data
public class ClassTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_time_id")
    private Long id;

    @Column(name = "class_time")
    private Time classTime;

    @Column
    private Time duration;

    @OneToMany
    @JoinColumn(name = "class_time_id")
    private List<Schedule> schedule;
}
