package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// @SpringBootTest: 스프링부트를 실행하여 스프링 컨테이너와 같이 테스트
@SpringBootTest
// @Transactinal: 테스트가 끝나면 트랜잭션을 롤백해줌 -> @BeforeEach, @AfterEach 제거
@Transactional
class MemberServiceIntegrationTest {
    // 테스트 케이스의 경우 필드 주입 가능
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    // 테스트 함수는 한글로 작성해도 무방함
    // 빌드될 때 테스트 코드는 실제 코드에 포함되지 않음
    void 회원가입() {
        // given: 주어진 상황에서
        Member member = new Member();
        member.setName("hello");

        // when: 무언가 실행했을 때
        Long savedId = memberService.join(member);

        // then: 이런 결과가 나와야 함
        Member findMember = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        // when
        List<Member> result = memberService.findMembers();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findOne() {
        // given
        Member member = new Member();
        member.setName("spring");
        memberRepository.save(member);

        // when
        Member result = memberService.findOne(member.getId()).get();

        // then
        assertThat(result).isEqualTo(member);
    }
}