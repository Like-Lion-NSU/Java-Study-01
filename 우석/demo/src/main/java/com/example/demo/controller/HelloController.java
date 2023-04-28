package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    // /hello라는 요청이 들어오면 아래의 메서드를 호출함
    public String hello(Model model){   // 웹 MVC의 Model
        // 모델의 data라는 속성에 hello!!라는 값을 넣음
        model.addAttribute("data", "hello!!");
        // 모델을 /resources/template 폴더 안에 있는 html 파일로 전달하여 렌더링
        return "hello";
    }

    @GetMapping("hello-mvc")
    // @RequestParam: 파라미터를 받는 어노테이션(웹브라우저에서 url 파라미터를 받아 변수에 저장함)
    public String helloMVC(@RequestParam("name") String name, Model model){
        // 파라미터로 받은 변수를 name이라는 속성에 넣음
        model.addAttribute("name", name);
        // 모델을 hello-template.html에 전달함
        return "hello-template";
    }

    @GetMapping("hello-string")
    // HTTP 응답의 Body 부분에 리턴값을 넣어 전달함
    @ResponseBody
    // view가 없어서 매개변수로 Model을 추가하지 않음
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    // 객체를 리턴
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // Hello 클래스
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
