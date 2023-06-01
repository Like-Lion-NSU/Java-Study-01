package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
    public List<Order> findAll(OrderSearch orderSearch){
        // 동적 쿼리가 아님!
        return em.createQuery("select o from Order o join o.member m " +
                        "where o.status = :status " +
                        "and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                // 최대 1000건 조회
                .setMaxResults(1000)
                .getResultList();
    }

    // JPQL 동적 쿼리 생성
    public List<Order> findAllByString(OrderSearch orderSearch) {
        // JPQL 문자열
        String jpql = "select o From Order o join o.member m";
        boolean isFirstCondition = true;

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000); // 최대 1000건

        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();
    }

    // JPA Criteria: JPA가 제공하는 표준 동적 쿼리 빌드 스펙
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);

        Join<Order, Member> m = o.join("member", JoinType.INNER); //회원과 조인
        List<Predicate> criteria = new ArrayList<>();

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"),
                    orderSearch.getOrderStatus());
            criteria.add(status);
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.<String>get("name"), "%" +
                            orderSearch.getMemberName() + "%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));

        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000); // 최대 1000건

        return query.getResultList();
    }

    // 패치조인 활용
    public List<Order> findAllWithMemberDelivery() {
        // order를 가져올 때 한번에 member와 delivery도 가져옴 (join과 동시에 select)
        // LAZY 옵션을 다 무시하고 값을 다 채워서 가져옴
        return em.createQuery("select o from Order o " +
                "join fetch o.member m " +
                "join fetch o.delivery d ", Order.class)
                .getResultList();
    }

    public List<Order> findAllWithItem() {
        // hibernate 버전 6부터는 distinct 명령어가 자동 수행됨
        return em.createQuery("select distinct o from Order o " +
                "join fetch o.member m " +
                "join fetch o.delivery d " +
                "join fetch o.orderItems oi " +
                "join fetch oi.item i ", Order.class)
                .getResultList();
    }
}
