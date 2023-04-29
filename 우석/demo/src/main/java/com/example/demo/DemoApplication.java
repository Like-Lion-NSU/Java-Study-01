package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication은 @ComponentScan 어노테이션을 포함함
// @ComponentScan: 이 클래스가 포함된 패키지 내에서 @Component 어노테이션이 작성된 클래스들을 스캔해서 자동으로 스프링 빈으로 등록해줌
// @Controller, @Service, @Repository 어노테이션은 @Component 어노테이션을 포함하기 때문에 자동으로 스프링 빈에 등록됨
// 스프링 컨테이너에서 스프링 빈으로 등록할 때 싱글톤으로 등록함(유일하게 하나만 등록하여 공유함)
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
