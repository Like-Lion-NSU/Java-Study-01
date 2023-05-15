package likelion.todo.repository;

import likelion.todo.domain.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemoryTodoRepository implements TodoRepository{
    private Map<Long, Todo> store = new HashMap<>();
    private Long sequence = 0L;
    @Override
    public Long save(Todo todo) {
        todo.setId(++sequence);
        store.put(todo.getId(), todo);
        return todo.getId();
    }

    @Override
    public Todo findOne(Long id) {
        return store.get(id);
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Todo> findByName(String name) {
        return store.values().stream()
                .filter(todo -> todo.getMember().getName().equals(name))
                .collect(Collectors.toList());
    }

    public void clearStore(){
        store.clear();
    }
}
