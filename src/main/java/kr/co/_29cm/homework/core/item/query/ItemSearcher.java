package kr.co._29cm.homework.core.item.query;

import kr.co._29cm.homework.core.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemSearcher {

    private final ItemSearcherRepository itemSearcherRepository;

    public List<Item> findAll() {
        return itemSearcherRepository.findAll();
    }

    public Item findByItemNo(Long itemNo) {
        return itemSearcherRepository.findById(itemNo).orElseThrow();
    }

    public List<Item> findByItemNo(List<Long> itemNos) {
        return itemSearcherRepository.findByIdIn(itemNos);
    }



}
