package com.example.demo.controller;

public class MemberForm {
    // <input type="text" id="name" name="name" placeholder="이름을 입력하세요">
    // 위 input 태그의 name 속성과 매칭됨
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
