package likelion.todo.service;

import likelion.todo.domain.Todo;
import likelion.todo.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final JpaRepository jpaRepository;
    public TodoService(JpaRepository memberRepository) {
        this.jpaRepository = memberRepository;
    }

    public Long save (Todo todo) {
        jpaRepository.save(todo);
        return todo.getId();
    }

    public List<Todo> findAllTodo() {
        return jpaRepository.findAllTodo();
    }

    public Todo findOneTodo(Long todoId) {
        return jpaRepository.findOneTodo(todoId);
    }
}
