package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Lombok: final 필드 생성자
@RequiredArgsConstructor
public class ItemRepository {
    // 스프링 Data JPA가 엔티티 매니저 주입
    private final EntityManager em;

    public void save(Item item){
        // JPA에 저장하기 전까지 id 값이 없음 -> 신규 등록
        if(item.getId() == null){
            em.persist(item);
        }else {
            // 이미 DB에 등록된 객체 -> update
            em.merge(item);
        }
    }

    public Item findOne (Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
