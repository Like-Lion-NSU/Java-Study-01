package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    OneToOne, ManyToOne 관계 최적화
    Order
    Order -> Member
    Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        // 강제 Lazy 로딩
        for (Order order : all) {
            // 프록시객체를 가져올 때는 DB에 쿼리가 날라가지 않음
            // 프록시객체.getter() -> JPA가 쿼리를 날려서 실제 값을 가져옴
            order.getMember().getName();        // Member Lazy 강제 초기화
            order.getDelivery().getAddress();   // Delivery Lazy 강제 초기화
        }

        // 양방향 연관관계에서 문제 발생! order가 member를 부르고 member가 order를 부름, 무한루프...
        // Order를 참조하는 엔티티의 필드에 @JsonIgnore 어노테이션을 작성해야함
        return all;
    }
}
