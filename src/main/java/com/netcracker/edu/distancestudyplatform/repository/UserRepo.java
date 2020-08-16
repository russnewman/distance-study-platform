package com.netcracker.edu.distancestudyplatform.repository;

import com.netcracker.edu.distancestudyplatform.model.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepo<T extends AbstractUser> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);
    Optional<T> findByLogin(String login);
}
