package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service: 스프링이 실행될 때, 스프링 컨테이너가 서비스 객체를 만들고 스프링 빈으로 등록함
// @Transactional: JPA에서 모든 데이터 변경은 transaction 안에서 실행되어야함
// 이 어노테이션이 작성된 클래스에 있는 메서드를 실행할 때마다 트랜잭션이 시작됨
// 트랜잭션에서 메서드가 정상 종료되면 트랜잭션을 커밋하고 런타임 예외가 발생하면 롤백함
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // DI(Dependency Injection): MemberRepository를 외부에서 주입하도록 변경
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member){
        // 중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
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
        return memberRepository.findAll();
    }

    // 개인 회원 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
