package likelion.todo.repository;

import likelion.todo.domain.Todo;

import java.util.List;

public interface TodoRepository {
    Long save(Todo todo);
    Todo findOne(Long id);
    List<Todo> findAll();
    List<Todo> findByName(String name);
}
