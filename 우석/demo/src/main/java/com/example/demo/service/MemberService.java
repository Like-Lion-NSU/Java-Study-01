package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service: 스프링이 실행될 때, 스프링 컨테이너가 서비스 객체를 만들고 스프링 빈으로 등록함
// @Transactional: JPA에서 모든 데이터 변경은 transaction 안에서 실행되어야함
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // DI(Dependency Injection): MemberRepository를 외부에서 주입하도록 변경
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member){
        long start = System.currentTimeMillis();
        try{
            // 중복 회원 검증
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    // extract method
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                // Optional 객체의 값이 존재하면
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
    }

    // 개인 회원 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
