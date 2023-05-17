package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
// @NoArgsConstructor: 기본 생성자
// AccessLevel.PROTECTED: 접근 제어자를 protected로 설정하여 외부에서 사용하지 못하게 함
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    // 생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        // 주문상품 생성
        OrderItem orderItem = new OrderItem();

        // 주문상품 세팅
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        // 재고 정리
        item.removeStock(count);

        // 주문을 생성할 때 주문상품을 받아서 주문세팅을 해줌
        return orderItem;
    }

    // 비즈니스 로직

    // 주문 취소
    public void cancel() {
        getItem().addStock(count);
    }

    // 조회 로직
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
