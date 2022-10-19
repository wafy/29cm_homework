package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.core.item.Item;
import kr.co._29cm.homework.core.item.SoldOutException;
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

    @Transactional
    public void updateStock(List<ItemNoAndStock> itemNoAndStockList) throws SoldOutException {
        for (ItemNoAndStock itemNoAndStock : itemNoAndStockList) {
            Item findItem = itemRepository.findById(itemNoAndStock.getItemNo()).orElseThrow(
                    () -> new NoSuchItemException("요청한 상품번호가 존재하지 않습니다 :"+ itemNoAndStock.getItemNo()));

            log.info("item 조회 성공 = {}", findItem.getItemNo());
            int stock = 0;
            try {
                log.info("재고 차감 요청 재고={}", findItem.getStock());
                log.info("현재 재고 ={}", itemNoAndStock.getQuantity());
                stock = findItem.stockDeduction(itemNoAndStock.getQuantity());
                log.info("재고 차감요청={}", stock);
                findItem.setStock(stock);
                log.info("재고 차감 성공 후 재고={}", findItem.getStock());
            } catch (SoldOutException e) {
                log.info("재고 부족 [상품번호]={} [현재재고]={}, [차감후 재고]={}",
                        findItem.getItemNo(), findItem.getStock(), stock);
                throw new SoldOutException(e.getMessage());
            }
        }
    }
}
