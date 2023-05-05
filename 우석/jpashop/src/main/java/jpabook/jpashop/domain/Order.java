package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
// 테이블명 설정
// sql의 order by 절과 겹쳐서 orders로 변경
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // 다대일 관계, 지연로딩 설정
    @ManyToOne(fetch = FetchType.LAZY)
    // 외래키 설정, 연관관계 주인
    @JoinColumn(name = "member_id")
    private Member member;

    // 일대다 관계
    // CascadeType.ALL: 컬렉션에 persist를 전파(부모와 같이 진행)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 일대일 관계, 지연로딩 설정
    // CascadeType.ALL: 각각 진행하는 persist를 같이 진행하게 해줌
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // 외래키 설정, 연관관계 주인
    // Delivery 보다 Order에서 접근을 더 많이 함
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // 주문시간
    private LocalDateTime orderDate;

    // EumType.STRING: Enum 타입 값을 문자열로 지정
    @Enumerated(EnumType.STRING)
    // 주문상태 (ORDER, CANCEL)
    private OrderStatus status;

    // 연관관계 편의 메서드
    // 양방향 연관관계 설정을 원자적으로 묶는 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
