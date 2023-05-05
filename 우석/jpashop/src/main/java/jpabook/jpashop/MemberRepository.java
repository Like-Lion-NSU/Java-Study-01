package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    // @PersistenceContext: 스프링부트가 EntityManager를 자동으로 주입해줌
    // spring-data-jpa 라이브러리를 추가하면 자동으로 스프링 빈에 엔티티매니저를 등록
    @PersistenceContext
    private EntityManager em;

    // 커맨드와 쿼리 분리 원칙 -> 저장을 하고나면 가급적이면 사이드 이펙트 효과 때문에 리턴값을 거의 안 만들고 id값으로 조회하기 위해 id값을 리턴함
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
