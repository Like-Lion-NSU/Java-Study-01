package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    // Model에 데이터를 실어서 Controller에서 Model을 View에 넘김
    public String hello(Model model){
        // name이라는 키로 value값을 넘김
        model.addAttribute("data", "hello");
        // viewName(html 파일 이름) 리턴
        // 스프링부트의 타임리프가 /resources/templates/ 에서 {viewName}.html을 매핑해줌
        return "hello";
    }
}
