package com.example.demo;

import com.example.demo.Repository.*;
import com.example.demo.aop.TimeTraceAop;
import com.example.demo.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
//    private EntityManager em;
    @Autowired
    public SpringConfig(MemberRepository memberRepository, EntityManager em) {
        this.memberRepository = memberRepository;
//        this.em = em;
    }

//    private DataSource dataSource;
//
//    @Autowired //스프링 빈을 이용하여 데이터 베이스와 데이터소스를 연결시켜줌
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
    @Bean// 스프링 빈에 내가 직접 입력해주어서 컨테이너에 넣는 방식.
    public MemberService memberService() { //spring bean에 저장되어서 memberService에 넣어주고
        return new MemberService(memberRepository); // 그 MemberService는 memberRespository를 사용할수있게끔하고
    }

//    @Bean
//    public MemberRepository memberRepository() { // 똑같이 빈에 올려주고
//        return new MemoryMemberRepository(); // 레퍼지토리에 빈을 넣어준다.
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

//    @Bean
//    public TimeTraceAop TimeTraceAop() {
//        return new TimeTraceAop;
//    }
}
