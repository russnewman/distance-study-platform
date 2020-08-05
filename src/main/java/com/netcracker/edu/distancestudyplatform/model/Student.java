package com.netcracker.edu.distancestudyplatform.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
public class Student extends User {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
