package com.example.demo.repository;

import com.example.demo.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    // JPA는 모든게 EntityManger로 동작함
    private final EntityManager em;

    // EntityManager 주입
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist: 영속화, 영구저장
        // jpa가 insert 쿼리를 만들어서 db에 저장하고 엔티티에 id까지 설정해줌
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // find(엔티티 타입, pk값): pk 값으로 엔티티를 찾아줌
        // jpa가 selct 쿼리를 만들어줌
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // jpql이라는 객체지향 쿼리 언어를 사용하여 쿼리문을 작성해줘야함
        // creatQuery(쿼리문, 엔티티 타입): 엔티티를 대상으로 쿼리문을 만듬
        // 엔티티를 대상으로 쿼리를 날리면 sql로 번역이 됨
        // select m from Member m: 엔티티 객체 자체를 select 하여 조회함
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
