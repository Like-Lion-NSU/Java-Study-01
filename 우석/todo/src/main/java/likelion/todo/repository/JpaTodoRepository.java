package likelion.todo.repository;

import jakarta.persistence.EntityManager;
import likelion.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaTodoRepository implements TodoRepository{
    private final EntityManager em;
    @Override
    public Long save(Todo todo) {
        em.persist(todo);
        return todo.getId();
    }

    @Override
    public Todo findOne(Long id) {
        return em.find(Todo.class, id);
    }

    @Override
    public List<Todo> findAll() {
        return em.createQuery("select t from Todo t", Todo.class)
                .getResultList();
    }

    @Override
    public List<Todo> findByName(String name) {
        return em.createQuery("select t from Todo t join t.member m where m.name = :name", Todo.class)
                .setParameter("name", name)
                .getResultList();
    }
}
