package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// JPA는 인터페이스로 그 구현체로 Hibernate가 스프링에서 쓰임
// JPA는 ORM 기술로 객체(Object)와 릴레이션(Relation)을 매핑(Mapping)시킴
// @Entity: JPA가 관리하는 엔티티로 지정
@Entity
public class Member {
    // Pk 지정
    @Id
    // Identity 전략: DB가 id를 자동으로 생성해 주는 것
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 데이터를 구분하기 위해 시스템이 저장하는 임의의 값
    private Long id;
    // 이름
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
