package com.ramramv.springbootserver.user.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ramramv.springbootserver.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
