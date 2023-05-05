package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

// @Embeddable: JPA 내장타입, 어딘가 내장될 수 있음
// @Entity 어노테이션을 작성하지 않아서 테이블로 만들지 않고 pk인 id 필드도 존재하지 않음
@Embeddable
// 값은 변경이 되면 안 돼서 setter를 만들지 않음
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // JPA가 리플렉션, 프록시 등의 기술들을 사용하기 위해선 기본 생성자가 있어야 함
    // public으로 만들기보다는 protected로 설정
    protected Address() {

    }

    // 생성시에만 값을 할당
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
