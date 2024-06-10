package com.ramramv.springbootserver.auth.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ramramv.springbootserver.auth.dto.UserInfoResponse;
import com.ramramv.springbootserver.auth.entity.User;
import com.ramramv.springbootserver.auth.service.UserService;

import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/test")
    public String test() {
        return new String("Test");
    }

    @GetMapping("/")
    public String mainP() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return "Main Controller : " + name + " role :"
                + SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
