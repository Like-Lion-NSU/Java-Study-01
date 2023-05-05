package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
// dtype 값 지정
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item {
    private String director;
    private String actor;
}
