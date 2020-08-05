package com.netcracker.edu.distancestudyplatform.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculties")
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long id;

    @OneToMany(mappedBy = "faculty")
    private List<Group> groups;

    @Column(name = "faculty_name")
    private String name;

}
