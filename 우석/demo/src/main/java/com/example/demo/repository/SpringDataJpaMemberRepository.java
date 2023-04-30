package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속한 인터페이스가 있으면 스프링 데이터 JPA가 구현체를 자동으로 만들어서 스프링 빈에 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
