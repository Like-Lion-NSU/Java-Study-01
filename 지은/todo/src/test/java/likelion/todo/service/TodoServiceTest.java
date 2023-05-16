package likelion.todo.service;

import likelion.todo.domain.Todo;
import likelion.todo.repository.JpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TodoServiceTest {
    @Autowired TodoService todoService;
    @Autowired JpaRepository jpaRepository;

    @Test
    public void save() {
        //Given
        Todo todo = new Todo();
        todo.setTitle("공부하쟈");
        //When
        Long saveId = todoService.save(todo);
        //Then
        assertEquals(todo, jpaRepository.findOneTodo(saveId));
    }
}