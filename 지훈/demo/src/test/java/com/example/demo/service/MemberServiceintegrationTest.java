package com.example.demo.service;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.Repository.MemoryMemberRepository;
import com.example.demo.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceintegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    // 기본적으로 db에는 transaction 이라는 개념가 있는데, db에 데이터를 넣고 commit을 해야
    // 한다. 근데 만약 테스트 다하고 롤백을 해버리면 그 안에 데이터가 다 빠져나가게됨.
    // 말 그대로 @Transactional을 먼저 넣고 실행하게 되면 transaction을 먼저 읽고 나머지 코드를 실행하기 때문에
    // 원래 코드에는 db에 코드로 실행했던 데이터를 넣고 그 안에 저장을 해주는데 @Transactional로 인해서 다시 지워지는것
    // 그래서 다시 db에서 실행을 해도 저장된 데이터가 없는 것이다.(반영을 안한다고 생각하면 편함.)
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring100");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // 알트 엔터
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}