package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    // 다대다 관계
    @ManyToMany
    // 중간 테이블 매핑
    @JoinTable(name = "category_item",
            // 외래키 설정, 연관관계 주인
            joinColumns = @JoinColumn(name = "category_id"),
            // 반대방향 외래키 설정
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    // 다대일 관계, 지연로딩 설정
    @ManyToOne(fetch = FetchType.LAZY)
    // 외래키 설정, 연관관계 주인
    @JoinColumn(name = "parent_id")
    private Category parent;

    // 일대다 관계
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // 연관관계 편의 메서드
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
