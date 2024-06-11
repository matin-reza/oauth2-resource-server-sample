package com.example.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatinController {

    @RequestMapping("/test")
    public String root() {
        return "salam";
    }
}
