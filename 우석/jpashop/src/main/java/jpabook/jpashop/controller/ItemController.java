package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form){
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(book.getAuthor());
        book.setIsbn(book.getIsbn());

        itemService.saveItem(book);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        // 예제를 단순화 하기위해 다운캐스팅
        Book item = (Book) itemService.findItem(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    // @ModelAttribute: 요청 파라미터를 받아서 객체 안에 넣어줌, 생략가능
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form){
        // 실무에서는 유저가 아이템에 대해 수정할 권한을 체크해주는 로직이 있어야 한다.
        // 컨트롤러에서 엔티티를 생성헤서 파라미터로 전달하기보다는 form에서 필요한 값을 직접 전달해서 값을 변경하자
        // 전달할 데이터가 많다면 dto를 사용하자
//        Book book = new Book();

        // BookForm을 통해서 데이터가 넘어오지만 id 값이 세팅되어있음 = JPA가 관리했던 객체
        // 데이터베이스에 존재하는, 식별자 id 값이 있는 객체를 준영속 상태라고 부름
        // 여기서 book이 준영속 엔티티이고 new 연산자로 생성한 객체임
        // 트랜잭션 안에 있다고 해도 JPA가 관리하지 않아서 update를 할 수 없음
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(book.getAuthor());
//        book.setIsbn(book.getIsbn());

//        itemService.saveItem(book);
        // 변경감지를 이용해서 값을 업데이트함
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }
}
