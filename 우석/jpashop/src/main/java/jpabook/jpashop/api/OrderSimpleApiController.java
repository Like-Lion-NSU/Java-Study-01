package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        // N+1의 문제 -> 1번째 쿼리의 결과로 N번의 쿼리가 추가 실행되는 문제
        // 영속성 컨텍스트에 엔티티가 있는 경우에는 쿼리를 생략할 수 있음
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                // DTO로 변환되면서 LAZY 초기화 발생
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }

    @Data
    // API 스펙을 명확히 정의
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            this.orderId = order.getId();
            this.name = order.getMember().getName();    // LAZY 초기화: 영속성 컨텍스트가 id 값으로 찾고 없으면 db쿼리 날림
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();    // LAZY 초기화
        }
    }
}
