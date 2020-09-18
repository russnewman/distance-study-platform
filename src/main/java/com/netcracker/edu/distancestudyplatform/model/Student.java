package com.netcracker.edu.distancestudyplatform.model;


import lombok.*;

import javax.persistence.*;
;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
@NoArgsConstructor
public class Student extends AbstractUser {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "student")
    private List<Assignment> assignments;
}
