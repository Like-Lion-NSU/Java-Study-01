package com.example.demo;

import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    @Autowired
    // 스프링 데이터 JPA가 만든 구현체 주입
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // @Bean: 메서드를 실행하여 반환되는 객체를 스프링 빈에 등록
    @Bean
    public MemberService memberService(){
        // 스프링 빈에 등록된 memberRepository를 넣어줌
        return new MemberService(memberRepository);
    }
}
