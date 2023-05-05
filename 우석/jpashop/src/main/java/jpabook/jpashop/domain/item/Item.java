package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
// @Inheritance: 부모클래스에 상속 관계 전략 지정
// InheritanceType.SINGLE_TABLE: 싱글 테이블 전략
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
// 구분 컬럼명 설정
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
// 구현체를 가지기 때문에 추상클래스로 작성
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    // 다대다 연관관계
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
