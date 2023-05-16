package likelion.todo.dto;

import likelion.todo.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class TodoResponseDto {
    private String title;
    private boolean che;
    private LocalDateTime day;

    public TodoResponseDto(Todo todo){
        this.title = todo.getTitle();
        this.che = todo.isChe();
        this.day = todo.getDay();
    }

}
