package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {
    @Autowired
    EntityManager em;

    @Transactional
    public void updateTest() throws Exception{
        Book book = em.find(Book.class, 1L);

        // TX
        // 트랜잭션 안에서 값 변경이 일어남
        book.setName("spring");

        // 변경 감지 == dirty checking
        // TX commit
        // 트랜잭션 커밋 이후 JPA가 변경사항에 대해서 update 쿼리를 자동으로 생성해서 DB에 반영해줌
    }
}
