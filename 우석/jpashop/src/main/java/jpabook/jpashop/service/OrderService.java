package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        // 엔티티 조회
        Member member = memberRepository.findById(memberId).get();
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        // 예제를 단순화하기 위해 하나만 주문하도록 제한함
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        // createOrder 메서드: Order 객체를 생성하는 static 메서드로 기본 생성자의 사용을 제약하고 객체 생성 로직을 통일화함
        // JPA에서 엔티티의 기본 생성자의 접근제어자 protected 허용 -> 사용시 컴파일 오류
        // Lombok -> @NoArgsConstructor(access = AccessLevel.PROTECTED) : 기본생성자로 직접 객체를 생성하지 못하게 함
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        // cascade = CascadeType.ALL 속성 때문에 order를 저장할 때, delivery와 orderItem이 자동으로 저장됨
        // 참조하는 주인이 유일하고, 라이플 사이클을 동일하게 관리할 때, cascade를 사용
        // 만약 중요한 엔티티이고 다른데서도 사용한다면 별도의 리포지토리를 생성해서 관리를 해줘야함
        orderRepository.save(order);

        return order.getId();
    }

    // 주문 취소
    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        // 데이터 변경시 데이터를 update하는 쿼리를 날려야하지만, JPA를 활용하면 엔티티에서 데이터 변경이 일어날 때
        // JPA가 알아서 그 바뀐 변경 포인트들에 대해 더티 체킹(변경내역 감지)이 일어나면서 변경 내역을 update하는 쿼리를 날려줌
        order.cancel();

    }

    // 검색
    // 단순 조회 기능이나, 리포지토리에 단순하게 위임만 하는 로직은 컨트롤러에서 바로 리포지토리를 호출해도 된다.
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }
}
