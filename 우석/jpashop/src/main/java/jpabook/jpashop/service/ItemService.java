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

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findItem(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
