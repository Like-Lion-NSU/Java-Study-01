package likelion.todo.controller;

import likelion.todo.domain.Todo;
import likelion.todo.dto.TodoRequestDto;
import likelion.todo.dto.TodoResponseDto;
import likelion.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public List<TodoResponseDto> findAll(){
        List<Todo> todos = todoService.findAll();
        List<TodoResponseDto> dtos = todos.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
        return dtos;
    }

    @PostMapping
    public Long create(@RequestBody TodoRequestDto dto){
        Todo todo = TodoRequestDto.toEntity(dto);
        Long savedId = todoService.create(todo);
        return savedId;
    }

    @GetMapping("/id/{todoId}")
    public TodoResponseDto findById(@PathVariable("todoId") Long todoId){
        Todo todo = todoService.findOne(todoId);
        TodoResponseDto response = new TodoResponseDto(todo);
        return response;
    }

    @GetMapping("/name/{memberName}")
    public List<TodoResponseDto> findByName(@PathVariable("memberName") String memberName){
        List<Todo> todos = todoService.findByName(memberName);
        List<TodoResponseDto> dtos = todos.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
        return dtos;
    }
}
