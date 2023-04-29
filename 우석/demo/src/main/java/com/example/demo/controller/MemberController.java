package com.example.demo.controller;

import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @Controller: 스프링이 실행될 때, 스프링 컨테이너가 컨트롤러 객체를 만들고 스프링 빈으로 등록함
@Controller
public class MemberController {
    private final MemberService memberService;

    // DI(Dependency Injection): 스프링 컨테이너에 있는 MemberService를 연결시켜줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
