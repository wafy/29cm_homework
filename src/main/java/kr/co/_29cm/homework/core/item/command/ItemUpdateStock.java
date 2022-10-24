package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.core.item.Item;
import kr.co._29cm.homework.core.item.SoldOutException;
import kr.co._29cm.homework.core.item.query.ItemSearcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemUpdateStock {
    private final ItemRepository itemRepository;
    private final ItemSearcher itemSearcher;

    @Transactional
    public void updateStock(List<ItemNoAndStock> itemNoAndStockList) {
        for (ItemNoAndStock itemNoAndStock : itemNoAndStockList) {
            Item findItem = itemSearcher.findByItemNo(itemNoAndStock.getItemNo());

            int stock = 0;
            try {
                stock = findItem.getStock();
                findItem.decreaseStock(itemNoAndStock.getQuantity());
                itemRepository.saveAndFlush(findItem);
                log.info("재고 차감 후 재고={}", findItem.getStock());
            } catch (SoldOutException e) {
                log.info("재고 부족 [상품번호]={} [현재재고]={}, [차감후 재고]={}",
                        findItem.getItemNo(), findItem.getStock(), stock);
                throw new SoldOutException(e.getMessage(), findItem.getItemNo(), itemNoAndStock.getQuantity());
            }
        }
    }
}
