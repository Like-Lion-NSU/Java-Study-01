package likelion.todo.controller;

import likelion.todo.domain.Todo;
import likelion.todo.dto.TodoRequestDto;
import likelion.todo.dto.TodoResponseDto;
import likelion.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoController {
    private final TodoService todoService;
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(value = "/Todo/new")
    public String createForm() {
        return "Todo/createTodoForm";
    }

    @PostMapping(value = "/Todo/new")
    public TodoResponseDto create(TodoRequestDto dto) {
        Todo todo = TodoRequestDto.toEntity(dto);
        Long saveId = todoService.save(todo);

        Todo result = todoService.findOneTodo(saveId);
        TodoResponseDto response = new TodoResponseDto(result);
        return response;
    }

    @GetMapping(value = "/Todo/findAll")
    public List<TodoResponseDto> findAll () {
        List<Todo> result = todoService.findAllTodo();
        return result.stream().map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }
    @GetMapping(value = "/Todo/findOne")
    public TodoResponseDto findOne (long id) {
        Todo result = todoService.findOneTodo(id);
        TodoResponseDto response = new TodoResponseDto(result);
        return response;
    }



    @GetMapping(value = "/Todo")
    public String list(Model model) {
        List<Todo> todos = todoService.findAllTodo();
        model.addAttribute("members", todos);
        return "Todo/todoList";
    }
}
