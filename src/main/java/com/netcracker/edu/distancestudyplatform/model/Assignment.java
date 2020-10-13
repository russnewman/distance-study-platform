package com.netcracker.edu.distancestudyplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "assignments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long id;

    @Column(name = "grade")
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private DatabaseFile dbFile;

    @Column(name = "student_commentary")
    private String studentCommentary;


    @Column(name = "teacher_commentary")
    private String teacherCommentary;
}
