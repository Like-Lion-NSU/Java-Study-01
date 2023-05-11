package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        // 컨트롤러에서 뷰로 넘어갈때 데이터를 실어서 넘김
        // 빈 폼을 넘김
        model.addAttribute("memberForm", new MemberForm());

        // 회원 등록 화면으로 이동
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    // @Vaild: 어노테이션이 달린 필드(MemberForm)를 검증을 해줌
    // BindingResult: 발생된 오류가 담기는 객체, 결과가 화면까지 전달됨
    public String create(@Valid MemberForm form, BindingResult result){
        // 오류 처리
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        // 주소 생성
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        // 멤버 생성
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        // 멤버 등록
        memberService.join(member);

        // 홈 화면으로 이동
        return "redirect:/";
    }
}
