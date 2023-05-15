package likelion.todo.repository;

import likelion.todo.domain.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Todo todo = new Todo();
        todo.setTitle("A");
        //when
        repository.save(todo);
        //then
        Todo result = repository.findOneTodo(todo.getId()).get();
        assertThat(result).isEqualTo(todo);
    }

    @Test
    public void check() {
        Todo todo2 = new Todo();
        todo2.setTitle("D");
        //w
        repository.save(todo2);
        boolean result2 = repository.check(todo2);
        assertThat(result2).isEqualTo(true);
    }

    @Test
    public void findAllTodo() {
        //given
        Todo member1 = new Todo();
        member1.setTitle("B");
        repository.save(member1);
        Todo member2 = new Todo();
        member2.setTitle("C");
        repository.save(member2);
        //when
        List<Todo> result = repository.findAllTodo();
        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void findOneTodo() {
        //given
        Todo member1 = new Todo();
        member1.setTitle("B");
        repository.save(member1);
        Todo member2 = new Todo();
        member2.setTitle("C");
        repository.save(member2);
        //when
        Todo result = repository.findOneTodo(1).get();
        //then
        assertThat(result).isEqualTo(member1);
    }
}