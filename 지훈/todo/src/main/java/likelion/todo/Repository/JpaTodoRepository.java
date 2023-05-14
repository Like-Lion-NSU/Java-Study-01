package likelion.todo.Repository;

import jakarta.persistence.EntityManager;
import likelion.todo.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JpaTodoRepository implements TodoRepository {
    @Autowired
    private final EntityManager em;

    public JpaTodoRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Todo save(Todo todo) {
        em.persist(todo);
        return todo;
    }

    @Override
    public Optional<Todo> findById(Long id) {
        Todo todo = em.find(Todo.class, id);
        return Optional.ofNullable(todo);
    }

    @Override
    public Optional<Todo> findByName(String name) {
        List<Todo> result = em.createQuery("select t from Todo t where t.todo=:todo", Todo.class)
                .setParameter("todo", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Todo> findAll() {
        return em.createQuery("select t from Todo t", Todo.class)
                .getResultList();
    }
}