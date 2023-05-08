package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Lombok: final 필드 생성자
@RequiredArgsConstructor
public class OrderRepository {
    // 엔티티 매니저 주입
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    // OrderSearch: 검색용 dto
//    public List<Order> findAll(OrderSearch orderSearch){
//
//    }
}
