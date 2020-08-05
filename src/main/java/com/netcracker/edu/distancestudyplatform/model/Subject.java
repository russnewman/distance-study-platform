package com.netcracker.edu.distancestudyplatform.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjects_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "subject")
    private List<Schedule> schedule;
}
