package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    // 일대일 관계, 지연로딩 설정
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    // 내장 타입
    @Embedded
    private Address address;

    // @Enumerated: Enum 타입 지정
    // EnumType.ORDINAL: Enum 타입 값을 숫자로 지정, 중간에 추가 시 순서가 밀려 장애 발생 가능
    // EnumType.STRING: Enum 타입 값을 문자열로 지정
    @Enumerated(EnumType.STRING)
    // 배송상태 (READY, COMP)
    private DeliveryStatus status;
}
