package likelion.todo.repository;

import likelion.todo.domain.Member;
import likelion.todo.domain.Todo;
import likelion.todo.repository.MemoryTodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemoryTodoRepositoryTest {
    @Autowired
    private MemoryTodoRepository memoryTodoRepository;

    @AfterEach
    public void afterEach(){
        memoryTodoRepository.clearStore();
    }

    @Test
    public void save() throws Exception{
        // given
        Todo todo = new Todo();
        todo.setTitle("coding");

        // when
        Long savedId = memoryTodoRepository.save(todo);
        Todo result = memoryTodoRepository.findOne(savedId);

        // then
        assertThat(result).isEqualTo(todo);
    }

    @Test
    public void findAll() throws Exception{
        // given
        Todo todo1 = new Todo();
        todo1.setTitle("coding");
        memoryTodoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("debugging");
        memoryTodoRepository.save(todo2);

        // when
        List<Todo> result = memoryTodoRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void findByName() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Todo todo1 = new Todo();
        todo1.setTitle("coding");
        todo1.setMember(member1);
        memoryTodoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("debugging");
        todo2.setMember(member1);
        memoryTodoRepository.save(todo2);

        Member member2 = new Member();
        member2.setName("spring jpa");

        Todo todo3 = new Todo();
        todo3.setTitle("testing");
        todo3.setMember(member2);
        memoryTodoRepository.save(todo3);

        // when
        List<Todo> result = memoryTodoRepository.findByName("spring");

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}