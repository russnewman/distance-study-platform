package com.netcracker.edu.distancestudyplatform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "teachers")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends AbstractUser {
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Event> events;
}
