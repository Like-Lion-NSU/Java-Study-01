package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    // @Transactional: EntityManager를 통한 모든 데이터 변경은 항상 트랜잭션 안에서 이루어져야 함
    // 이 어노테이션이 없으면 오류 발생, 테스트 케이스에 있으면 테스트가 끝나고 롤백
    @Transactional
    // 롤백을 실행하지 않음
    @Rollback(false)
    public void testMember() throws Exception{
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        // 영속성 컨텍스트 안에서 id값이 같으면 같은 엔티티로 식별
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}