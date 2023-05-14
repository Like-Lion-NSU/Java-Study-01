package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
    // 정적 컨텐츠에서 설명했다싶이, 톰캣에서 받은 URL을 스프링 컨테이너에서 관련 컨트롤러가 있는지 찾는데,
    // 여기에 지금 관련 컨트롤러가 있기 때문에 이것만 호출되고 끝나고, 기존에 만들었던 index.html은 무시함.
}
