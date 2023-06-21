package jpabook.jpashop.repository.order.simplequery;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
// 컨트롤러에서 DTO를 정의하면 리포지토리에서 컨트롤러를 의존하게 됨
// 리포지토리에서 컨트롤러로 의존가능성을 없애기 위해 외부 클래스로 정의
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address){
        this.orderId = orderId;
        this.name = name;    // LAZY 초기화: 영속성 컨텍스트가 id 값으로 찾고 없으면 db쿼리 날림
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;    // LAZY 초기화
    }
}
