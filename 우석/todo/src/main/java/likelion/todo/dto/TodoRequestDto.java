package likelion.todo.dto;

import likelion.todo.domain.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoRequestDto {
    private String title;
    private Boolean isDone;

    public static Todo toEntity(TodoRequestDto dto){
        return Todo.builder()
                .title(dto.title)
                .isDone(dto.isDone)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
