package likelion.todo.repository;

import likelion.todo.domain.Member;
import likelion.todo.domain.Todo;

import java.util.List;
import java.util.Optional;

public interface MemberTodoRepository {
    Todo save(Todo todo);
    boolean check(Todo todo);
    List<Todo> findAllTodo();
    Optional<Todo> findOneTodo(long id);
//    Optional<Todo> findByName(String name);
}
