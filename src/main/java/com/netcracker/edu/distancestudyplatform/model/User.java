package com.netcracker.edu.distancestudyplatform.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  ="user_id")
    private Long id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String login;

    @Column
    private String password;
}
