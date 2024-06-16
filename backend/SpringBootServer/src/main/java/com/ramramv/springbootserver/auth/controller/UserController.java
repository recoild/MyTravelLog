package com.ramramv.springbootserver.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramramv.springbootserver.auth.dto.UserInfoResponse;
import com.ramramv.springbootserver.auth.entity.User;
import com.ramramv.springbootserver.auth.service.UserService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/session")
    public ResponseEntity<UserInfoResponse> getSession(Authentication authentication) {
        try {
            if (authentication == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            if (authentication.isAuthenticated()) {
                UserInfoResponse userInfoResponse = new UserInfoResponse();
                if (authentication.getPrincipal() instanceof OAuth2User oauthUser) {
                    String email = oauthUser.getAttribute("email");
                    userInfoResponse.setEmail(email);
                    User user = userService.getUserByEmail(userInfoResponse.getEmail());
                    userInfoResponse.setNickname(user.getNickname());
                    userInfoResponse.setRole(user.getRole().name());
                    userInfoResponse.setPicture(user.getPicture());
                    return ResponseEntity.ok(userInfoResponse);
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("/test")
    public String getMethodName() {
        return new String("test");
    }

    @GetMapping("/noauth")
    public ResponseEntity<String> getNoAuth() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("no auth");
    }

}
