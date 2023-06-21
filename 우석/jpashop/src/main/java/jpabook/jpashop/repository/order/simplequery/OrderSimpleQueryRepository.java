package jpabook.jpashop.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
// 성능 최적화를 위한 쿼리용 리포지토리
// 리포지토리는 순수한 엔티티를 조회하는데 쓰임
// 화면에 의존적인 로직은 리포지토리와 분리
public class OrderSimpleQueryRepository {
    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {
        // DTO로 반환하기 위해선 new 연산자을 써야함
        return em.createQuery("select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                        "from Order o " +
                        "join o.member m " +
                        "join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
