package com.example.demo;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // @Bean: 메서드를 실행하여 반환되는 객체를 스프링 빈에 등록
    @Bean
    public MemberService memberService(){
        // 스프링 빈에 등록된 memberRepository를 넣어줌
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
