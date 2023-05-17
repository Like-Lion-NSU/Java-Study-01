package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 읽기 전용 트랜젝션
@Transactional(readOnly = true)
// Lombok: final 필드 생성자
@RequiredArgsConstructor
public class ItemService {
    // 리포지토리 주입
    private final ItemRepository itemRepository;

    // 쓰기 트랜젝션
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    // item 업데이트시 변경감지를 이용!
    // merge()와 작동 방식이 같음, merge()는 변경된 객체를 리턴해줌
    @Transactional
    public void updateItem(Long id, String name, int price, int stockQuantity){
        // id를 기반으로 DB에 있는 영속상태의 엔티티를 가져옴
        // 트랜잭션 안에서 엔티티를 조회해야 영속상태로 관리됨
        Item findItem = itemRepository.findOne(id);
        // 값 변경
        // setter를 이용해 값을 변경하기 보다는 별도의 의미있는 메서드를 만들어서 변경지점을 추적할 수 있게 해야함
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        // 트랜잭션이 커밋되는 시점에 JPA가 flush를 날려서 영속성 컨텍스트에서 변경된 엔티티를 찾고 변경된 값들에 대해 update 쿼리를 DB에 날림
        // 변경감지가 일어나 merge()를 하지 않아도 됨
//        itemRepository.save(findItem);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findItem(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
