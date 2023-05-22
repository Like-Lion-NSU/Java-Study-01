package likelion.todo.dto;

import likelion.todo.domain.Todo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoRequestDto {
    private String title;
    private boolean che;

    public static Todo toEntity(TodoRequestDto dto){
        return Todo.builder()
                .title(dto.title)
                .che(dto.che)
                .day(LocalDateTime.now())
                .build();
    }
}
