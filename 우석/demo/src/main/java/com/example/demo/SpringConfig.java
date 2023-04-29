package com.example.demo;

import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // 스프링 빈이 엔티티 매니저를 만들어 줌
    private EntityManager em;

    // EntityManager 주입
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    // @Bean: 메서드를 실행하여 반환되는 객체를 스프링 빈에 등록
    @Bean
    public MemberService memberService(){
        // 스프링 빈에 등록된 memberRepository를 넣어줌
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        // MemberRepository 구현체만 JDBC 리포지토리로 변경
//        return new JdbcMemberRepository(dataSource);
        // MemberRepository 구현체만 JDBC Template 리포지토리로 변경
//        return new JdbcTemplateMemberRepository(dataSource);
        // MemberRepository 구현체만 JPA 리포지토리로 변경
        return new JpaMemberRepository(em);
    }
}
