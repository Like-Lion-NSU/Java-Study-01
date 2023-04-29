package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 도메인
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
