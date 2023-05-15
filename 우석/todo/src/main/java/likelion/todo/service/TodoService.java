package likelion.todo.service;

import likelion.todo.domain.Todo;
import likelion.todo.repository.JpaTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {
    private final JpaTodoRepository jpaTodoRepository;

    @Transactional
    public Long create(Todo todo){
        return jpaTodoRepository.save(todo);
    }

    public Todo findOne(Long todoId){
        return jpaTodoRepository.findOne(todoId);
    }

    public List<Todo> findAll(){
        return jpaTodoRepository.findAll();
    }

    public List<Todo> findByName(String name){
        return jpaTodoRepository.findByName(name);
    }
}
