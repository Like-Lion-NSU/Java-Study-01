package likelion.todo.repository;

import jakarta.persistence.EntityManager;
import likelion.todo.domain.Member;
import likelion.todo.domain.Todo;
import likelion.todo.repository.JpaTodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class JpaTodoRepositoryTest {
    @Autowired
    private JpaTodoRepository jpaTodoRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void save() throws Exception{
        // given
        Todo todo = new Todo();
        todo.setTitle("coding");

        // when
        Long savedId = jpaTodoRepository.save(todo);
        Todo result = jpaTodoRepository.findOne(savedId);

        // then
        assertThat(result).isEqualTo(todo);
    }

    @Test
    public void findAll() throws Exception{
        // given
        Todo todo1 = new Todo();
        todo1.setTitle("coding");
        jpaTodoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("debugging");
        jpaTodoRepository.save(todo2);

        // when
        List<Todo> result = jpaTodoRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void findByName() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("spring");
        em.persist(member1);

        Todo todo1 = new Todo();
        todo1.setTitle("coding");
        todo1.setMember(member1);
        jpaTodoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("debugging");
        todo2.setMember(member1);
        jpaTodoRepository.save(todo2);

        Member member2 = new Member();
        member2.setName("spring jpa");
        em.persist(member2);

        Todo todo3 = new Todo();
        todo3.setTitle("testing");
        todo3.setMember(member2);
        jpaTodoRepository.save(todo3);

        // when
        List<Todo> result = jpaTodoRepository.findByName("spring");

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}