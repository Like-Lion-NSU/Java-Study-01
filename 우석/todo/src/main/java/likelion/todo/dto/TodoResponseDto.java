package likelion.todo.dto;

import likelion.todo.domain.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponseDto {
    private String title;
    private Boolean isDone;
    private LocalDateTime createdDate;

    public TodoResponseDto(Todo todo){
        this.title = todo.getTitle();
        this.isDone = todo.getIsDone();
        this.createdDate = todo.getCreatedDate();
    }
}
