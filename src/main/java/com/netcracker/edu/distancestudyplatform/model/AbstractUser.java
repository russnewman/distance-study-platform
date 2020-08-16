package com.netcracker.edu.distancestudyplatform.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractUser implements User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  ="user_id")
    private Long id;
    @Column(unique = true)
    private String email;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column
    private boolean active;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_2_authorities",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    private Set<Authority> authorities;
}
