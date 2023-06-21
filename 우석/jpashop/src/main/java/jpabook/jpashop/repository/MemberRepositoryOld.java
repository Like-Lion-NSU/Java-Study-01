package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryOld {
    // 스프링이 엔티티 매니저를 만들어서 주입해줌
    private final EntityManager em;

    public void save(Member member){
        // 영속성 컨텍스트에 엔티티 객체를 넣음
        // 트랜잭션이 커밋되는 시점에 DB에 반영됨, insert 쿼리 생성
        em.persist(member);
    }

    public Member findOne(Long id){
        // find(엔티티 타입, pk) -> 단건 조회
       return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        // JPQL 작성 -> SQL로 번역
        // SQL: 테이블 대상으로 쿼리를 날림, JPQL: 엔티티 객체를 대상으로 쿼리를 날림
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
