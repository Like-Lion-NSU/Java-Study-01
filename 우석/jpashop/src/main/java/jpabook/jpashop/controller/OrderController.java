package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){
        // 회원과 상품 선택을 위한 리스트 조회
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        // 리스트 전달
        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    // @RequestaParam: form 태그의 name 속성으로 value 값이 넘어오는 것을 변수에 바인딩해줌
    public String createOrder(@RequestParam("memberId") Long memberId,
                              @RequestParam("itemId") Long itemId,
                              @RequestParam int count){
        // 주문 생성
        orderService.order(memberId, itemId, count);

        // 주문 내역 목록 화면으로 이동
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    // OrderSearch: 검색 조건을 받는 객체(회원 이름, 주문 상태)
    // @ModelAttribute: thymeleaf에서 form 태그의 object 값을 받음, 자동으로 model에 담겨짐
    // form이 submit될 때, orderSearch로 get 방식으로 값이 넘어와 orderSerch에 값이 바인딩 됨
    // 그 객체로 검색을 하고 그 결과를 order/orderList로 값을 전달함
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        // 생략된 코드
//        model.addAttribute("orderSearch", orderSearch);

        return "order/orderList";
    }

    @PostMapping("orders/{orderId}/cancel")
    public String cancleOrder(@PathVariable("orderId") Long orderId){
        // 주문 취소
        orderService.cancelOrder(orderId);
        // 주문 내역으로 이동
        return "redirect:/orders";
    }
}
