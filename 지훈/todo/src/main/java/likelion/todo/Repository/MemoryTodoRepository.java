package likelion.todo.Repository;

import likelion.todo.domain.Member;
import likelion.todo.domain.Todo;

import java.util.*;

public class MemoryTodoRepository implements TodoRepository {
    private static Map<Long, Todo> store = new HashMap<Long, Todo>();

    private static long sequence = 0L;

    @Override
    public Todo save(Todo todo) {
        todo.setId(sequence++);
        store.put(todo.getId(), todo);
        return todo;
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Todo> findByName(String name) {
        return store.values().stream()
                .filter(todo -> todo.getMember().getName().equals(name))
                .findAny();
    }

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
