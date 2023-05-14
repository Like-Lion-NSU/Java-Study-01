package likelion.todo.Repository;

import likelion.todo.domain.Member;
import likelion.todo.domain.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    Todo save(Todo todo);

    Optional<Todo> findById(Long id);
    Optional<Todo> findByName(String name);

    List<Todo> findAll();
}
