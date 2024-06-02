package com.ramramv.springbootserver.auth.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ramramv.springbootserver.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
