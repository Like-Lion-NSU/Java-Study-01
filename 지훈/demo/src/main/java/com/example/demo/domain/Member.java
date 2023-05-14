package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//orm object relational mapping
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 우리가 db에 값을 넣으면 db가 id를 자동으로 생성.
    private Long id;

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
