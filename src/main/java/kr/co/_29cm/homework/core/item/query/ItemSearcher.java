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

}