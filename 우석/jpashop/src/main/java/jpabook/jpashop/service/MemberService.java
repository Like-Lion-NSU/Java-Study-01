package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// JPA의 모든 데이터 변경은 트랜잭션 안에서 실행되어야 함
// @Transactional 어노테이션을 클래스 레벨에서 사용하면 public 메서드들은 자동으로 트랜잭션에 들어감
// @Transactional(readOnly = true): 읽기 전용, JPA의 조회 성능을 최적화 함
@Transactional(readOnly = true)
// Lombok - @RequiredArgsConstructor: 모든 final 필드를 가지고 생성자를 만들어줌
@RequiredArgsConstructor
public class MemberService {

    // 스프링이 스프링 빈에 등록된 객체를 자동 주입
    private final MemberRepository memberRepository;

    // 회원 가입
    // @Transactional: 쓰기
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증 -> 데이터베이스의 unique 제약조건 설정해야 안전
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
