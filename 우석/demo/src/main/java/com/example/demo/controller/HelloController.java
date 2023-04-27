package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")    // /hello라는 요청이 들어오면 아래의 메서드를 호출함
    public String hello(Model model){   // 웹 MVC의 Model
        // 모델의 data라는 속성에 hello!!라는 값을 넣음
        model.addAttribute("data", "hello!!");
        // 모델을 /resources/template 폴더 안에 있는 html 파일로 전달하여 렌더링
        return "hello";
    }
}
