package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    // 컬럼명 설정
    @Column(name = "member_id")
    private Long id;

    private String name;

    // @Embedded: 내장 타입을 포함함
    @Embedded
    private Address address;

    // 일대다 관계
    // 양방향 연관관계, order 테이블에 있는 member 필드에 의해서 매핑됨
    @OneToMany(mappedBy = "member")
    // @JsonIgnore: json으로 반환할 때 무시함
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();
}
