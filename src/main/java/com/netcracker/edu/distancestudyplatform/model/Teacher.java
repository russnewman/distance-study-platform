package com.netcracker.edu.distancestudyplatform.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "teachers")
@EqualsAndHashCode(callSuper = true)
public class Teacher extends AbstractUser {
    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private List<Schedule> schedules;
}
