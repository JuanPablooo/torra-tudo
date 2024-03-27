package com.torra.tudo.discovery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test-status")
public class Status {

    @GetMapping
    public String test(){
        System.out.printf("teste-status");
        return "ok";
    }
}
