package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;
import java.util.List;

@Controller
public class MemberController {
    // 스프링이 시작될때 Container이라는 통이 생기게 되는데 그곳에 @Controller가 있다면 저 클래스 객체를 생성하여 넣어둔다.
    //이것을 스프링 빈이 관리 된다고 말한다.
    private MemberService memberService;
    @Autowired
    // 스프링이 스프링 컨테이너에 있는 memberService와 연결시켜줌
    // Autowired를 쓸때 이 컨트롤러를 실행하게 되면, 스프링 빈에있는 memberService를 가져와 여기에 넣어주는데
    // 이것을 dependencyinjection 이라고 한다. 의존관계를 넣어주는 것이다.
    public MemberController(MemberService memberService) {
        // 하지만 그냥 실행하게 된다면 지금 memberService 클래스 객체 코드는 순수한 자바 클래스기 때문에
        //알수가 없다. 지금 이 코드는 @Autowired로 나마 찾을수 있지만, 본래 코드는 찾을수 없다. 그래서 @Service를 memberService객체에 넣어준다.
        //레퍼지토리도 동일. 그러면 컴포넌트 아무때나 사용할수 있느냐? ㄴㄴ 데모에플리케이션이 허용하는 범위내에서만 스프링이 뒤지는 것.
        this.memberService = memberService;
        System.out.println("memberService =" + memberService.getClass());

    }

    // @Autowired private MemberService memberService; 필드 주입

//    @Autowired // 생성자 주입
//    public MemberController(MemberService memberService) {
//      this.memberService = memberService;
//    }

    //@Autowired // setter 주입
    //public setMemberService(MemberService memberService) {
    //  this.memberService = memberService;
    //}


    @GetMapping("/members/new")
    //홈페이지에서 회원가입에 들어가게 되면 시작.
    //URL의 get방식으로 들어오게 되고, 이 코드가 실행이 되면 밑에 있는 코드와 같이
    // 그냥 바로 members/createMemberForm으로 이동을 시킴. 템플릿으로 이동.
    // 템플릿에 있는 html이 뿌려져서 html의 기능을 열음.
    public String createForm() {
        return "members/createMemberForm";
    }
@PostMapping("/members/new")
    //GetMapping이 조회하는거라고 하면 PostMapping은 등록하는 것이라고 생각.
    // 같은 주소지만, Get은 URL창에 조회하면 그 홈페이지로 이동을 하고, 이 코드는 그안에서, 등록을 하게끔 만들어줌
    // 그렇기 때문에 밑에 코드도 어떤 값을 입력하고 등록 했을때, 얻어지는 값을 의미.

    public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    memberService.join(member);

    return "redirect:/";
    }

    @GetMapping("/members")
    // 회원 목록을 볼 수 있게 만든 코드이다.
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
