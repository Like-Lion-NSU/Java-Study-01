package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

// JUnit 실행시 스프링이랑 엮어서 실행
@RunWith(SpringRunner.class)
// 스프링부트 통합 테스트, 없으면 @Autowired 실패
@SpringBootTest
// @Transactional 어노테이션이 테스트 케이스에 있으면 테스트 종료시 롤백
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        // 트랜잭션이 commit 될 때 flush가 되면서 JPA 영속성 컨텍스트에 있는 엔티티 객체가 insert 쿼리가 만들어지면서 DB에서 쿼리가 실행됨
        // 그래서 테스트 중에는 롤백되어 insert 쿼리가 실행되지 않음
        Long savedId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findById(savedId).get());
        // 같은 트랜잭션 안에서 같은 pk 값이 같은 엔티티는 같은 영속성 컨텍스트 안에서 하나로 관리됨
    }

    // @Test(expected): 예상되는 예외 작성
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_조회() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        // 예외가 발생해야 한다!
        memberService.join(member2);

        // then
        fail("예외가 발생해야 한다.");
    }
}