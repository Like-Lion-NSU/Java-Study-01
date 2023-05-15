package likelion.todo.repository;

import likelion.todo.domain.Member;
import likelion.todo.domain.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberTodoRepository {
    private static Map<Long, Todo> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Todo save(Todo todo) {
        todo.setId(++sequence);
        store.put(todo.getId(), todo); //id를 name으로 갈아끼워야 할듯?
        return todo;
    }

    @Override
    public boolean check(Todo todo) {
        todo.setChe(!todo.isChe());
        return todo.isChe();
    }

    @Override
    public List<Todo> findAllTodo() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Todo> findOneTodo(long id) {
        return Optional.ofNullable(store.get(id));
    }

//    @Override
//    public Optional<Todo> findByName(String name) {
//
//    }

    public void clearStore(){
        store.clear();
    }

}
