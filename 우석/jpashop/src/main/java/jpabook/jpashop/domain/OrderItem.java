package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    // 다대일 관계, 지연로딩 설정
    @ManyToOne(fetch = FetchType.LAZY)
    // 외래키 설정, 연관관계 주인
    @JoinColumn(name = "item_id")
    private Item item;

    // 다대일 관계, 지연로딩 설정
    @ManyToOne(fetch = FetchType.LAZY)
    // 외래키 설정, 연관관계 주인
    @JoinColumn(name = "order_id")
    private Order order;

    // 주문 가격
    private int orderPrice;
    // 주문 수량
    private int count;
}
