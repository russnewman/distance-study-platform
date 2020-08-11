package com.netcracker.edu.distancestudyplatform.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
@NoArgsConstructor
public class Student extends User {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
