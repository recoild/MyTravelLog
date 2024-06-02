package com.ramramv.springbootserver.user.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ramramv.springbootserver.user.dto.UserDto;
import com.ramramv.springbootserver.user.entity.User;
import com.ramramv.springbootserver.user.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/v1/register")
    public UserDto registerNewUser(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userService.registerNewUser(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @GetMapping("/hellowlrld")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getMethodName() {
        return new String("admin page");
    }

    @GetMapping("/")
    public String mainP() {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return "Main Controller : " + name + " role :"
                + SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
