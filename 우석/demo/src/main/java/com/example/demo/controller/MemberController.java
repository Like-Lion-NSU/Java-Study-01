package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// @Controller: 스프링이 실행될 때, 스프링 컨테이너가 컨트롤러 객체를 만들고 스프링 빈으로 등록함
@Controller
public class MemberController {
    private final MemberService memberService;

    // DI(Dependency Injection): 스프링 컨테이너에 있는 MemberService를 연결시켜줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 창으로 이동
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // 회원가입
    @PostMapping("/members/new")
    // 스프링이 전달받은 값을 MemberForm 클래스의 setter 메서드로 값을 설정
    public String create(MemberForm form){
        // 회원 만들기
        Member member = new Member();
        member.setName(form.getName());

        // 회원 등록
        memberService.join(member);

        // 홈 화면으로 이동
        return "redirect:/";
    }
}
