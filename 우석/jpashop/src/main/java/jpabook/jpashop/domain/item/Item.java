package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
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

    // 비즈니스 로직
    // 도메인 주도 설계, 엔티티 자체가 해결할 수 있는 문제는 엔티티 안에 비즈니스 로직 추가, 응집도 ↑

    // 재고 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    // 재고 감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        // 재고 체크
        if(restStock < 0){
            // 사용자 예외 NotEnoughStockException 발생
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity -= quantity;
    }
}
