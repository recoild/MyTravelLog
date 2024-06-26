package com.ramramv.springbootserver.auth.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ramramv.springbootserver.auth.entity.Role;
import com.ramramv.springbootserver.auth.entity.User;
import com.ramramv.springbootserver.auth.jpa.UserRepository;
import com.ramramv.springbootserver.auth.entity.QUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JPAQueryFactory queryFactory;

    public User registerNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        QUser qUser = QUser.user;
        return queryFactory.selectFrom(qUser).fetch();
    }

}
