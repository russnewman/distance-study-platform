package com.netcracker.edu.distancestudyplatform.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
@NoArgsConstructor
public class Student extends AbstractUser {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
