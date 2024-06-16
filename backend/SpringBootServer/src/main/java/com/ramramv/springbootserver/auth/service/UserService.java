package com.ramramv.springbootserver.auth.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ramramv.springbootserver.auth.entity.QUser;
import com.ramramv.springbootserver.auth.entity.Role;
import com.ramramv.springbootserver.auth.entity.User;
import com.ramramv.springbootserver.auth.jpa.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final JPAQueryFactory queryFactory;

    public UserService(UserRepository userRepository,
            JPAQueryFactory queryFactory) {
        this.userRepository = userRepository;
        this.queryFactory = queryFactory;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.debug("loadUser");
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String picture = (String) attributes.get("picture");

        // 사용자 정보 저장 또는 업데이트
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setNickname(name);
            newUser.setPicture(picture);
            newUser.setRole(Role.ROLE_USER);
            userRepository.save(newUser);
        } else {
            User existUser = user.get();
            existUser.setNickname(name);
            existUser.setPicture(picture);
            existUser.setRole(Role.ROLE_USER);
            userRepository.save(existUser);
        }

        log.debug("loadUser done");
        return oAuth2User;

    }

    public User getUserByEmail(String email) {
        QUser user = QUser.user;

        return queryFactory.selectFrom(user)
                .where(user.email.eq(email))
                .fetchOne();
    }

}
