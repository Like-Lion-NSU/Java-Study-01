package likelion.todo.Repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import likelion.todo.domain.Member;
import likelion.todo.domain.Todo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class JpaTodoRepositoryTest {
    @Autowired JpaTodoRepository repository;
    @Autowired EntityManager em;


    @Test
    void save() {
        Todo todo = new Todo();
        todo.setTodo("todo");

        repository.save(todo);

        repository.findByName()

    }

    @Test
    void findById() {
        Todo todo1 = new Todo();
        todo1.setTodo("svd");

        repository.save(todo1);

        Todo result = repository.findById(todo1.getId()).get();
        assertThat(todo1).isEqualTo(result);
    }

    @Test
    void findByName() {
        Member member = new Member();
        member.setName("김지훈");
        em.persist(member);

        Todo todo2 = new Todo();
        todo2.setTodo("김지훈 ");

        repository.save(todo2);

        Todo result2 = repository.findByName("김지훈").get();
        assertThat(todo2).isEqualTo(result2);
    }


    @Test
    void findAll() {
        Todo todo3 = new Todo();
        todo3.setDate(LocalDateTime.now());
        repository.save(todo3);

        Todo todo4 = new Todo();
        todo4.setCheck(false);
        repository.save(todo4);

        List<Todo> result3 = repository.findAll();
        assertThat(result3.size()).isEqualTo(2);
    }
}