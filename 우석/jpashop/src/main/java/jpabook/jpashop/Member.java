package jpabook.jpashop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
// Lombok: getter와 setter 메서드를 만들어 줌
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
}
