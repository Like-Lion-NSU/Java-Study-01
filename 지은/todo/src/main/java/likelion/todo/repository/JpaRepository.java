package likelion.todo.repository;

import jakarta.persistence.EntityManager;
import likelion.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class JpaRepository {
    private final EntityManager em;
    public void save(Todo todo) {
        em.persist(todo);
    }
    public boolean check(Todo todo) {
        todo.setChe(!todo.isChe());
        return todo.isChe();
    }
    public List<Todo> findAllTodo() {
        return em.createQuery("select m from Todo m", Todo.class)
                .getResultList();
    }
    public Todo findOneTodo(long id) {
        return em.find(Todo.class, id);
    }
}
