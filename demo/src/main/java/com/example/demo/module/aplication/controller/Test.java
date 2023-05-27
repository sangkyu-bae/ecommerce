package com.example.demo.module.aplication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Test {

    @GetMapping("/user")
    public String tt(){
        log.info("test {}",1);
        return System.getProperty("user.dir");
    }
}
