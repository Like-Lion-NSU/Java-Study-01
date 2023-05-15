package likelion.todo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Todo {
    @Id @GeneratedValue
    private String day;
    private boolean che = false;
    private String title;
    private long id;

}
