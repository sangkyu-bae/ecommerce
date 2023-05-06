package com.example.demo.module.aplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Test {

    @GetMapping("/")
    public String tt(){
        return System.getProperty("user.dir");
    }
}
