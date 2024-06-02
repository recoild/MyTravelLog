package com.ramramv.springbootserver.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HelloWorldController {
    private final Environment env;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Hello World");
        log.info("Hello World");
        return "Hello wd!";
    }

    @GetMapping("/check")
    public String check() {
        log.info("check");
        return String.format("Service 1 is running on port %s",
                env.getProperty("local.server.port"));
    }
}
