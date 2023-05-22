package likelion.todo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id @GeneratedValue
    private Long id;
    private boolean che = false;
    private String title;
    private LocalDateTime day;

}
