package likelion.todo.Repository;

import likelion.todo.domain.Member;
import likelion.todo.domain.Todo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryTodoRepositoryTest {
    MemoryTodoRepository repository = new MemoryTodoRepository();
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        Todo todo = new Todo();
        todo.setTodo("잡것");

        repository.save(todo);

        Todo result = repository.findById(todo.getId()).get();
        assertThat(todo).isEqualTo(result);

    }

    @Test
    public void findById() {
        Todo todo4 = new Todo();
        todo4.setId(123L);
        repository.save(todo4);

        Todo result3 = repository.findById(todo4.getId()).get();
        assertThat(todo4).isEqualTo(result3);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("홍길동");

        Todo todo1 = new Todo();
        todo1.setMember(member);
        repository.save(todo1);

        Todo result1 = repository.findByName("홍길동").get();
        assertThat(result1).isEqualTo(todo1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setId(3L);

        Todo todo2 = new Todo();
        todo2.setMember(member1);
        repository.save(todo2);

        Todo todo3 = new Todo();
        todo3.setMember(member1);
        repository.save(todo3);

        List<Todo> result2 = repository.findAll();

        assertThat(result2.size()).isEqualTo(2);
    }

}