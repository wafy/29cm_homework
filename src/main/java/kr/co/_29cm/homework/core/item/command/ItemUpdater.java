package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.core.cart.Cart;
import kr.co._29cm.homework.core.cart.query.CartSearcherRepository;
import kr.co._29cm.homework.core.item.Item;
import kr.co._29cm.homework.core.item.SoldOutException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemUpdater {
    private final ItemRepository itemRepository;
    private final CartSearcherRepository cartSearcherRepository;

    public void update(String sessionId) throws SoldOutException {
        log.info("itemNo와 quantity 조회");
        List<ItemNoAndStock> itemNoAndStockList = itemNoAndStocks(sessionId);
        updateStock(itemNoAndStockList);
    }

    private void updateStock(List<ItemNoAndStock> itemNoAndStockList) throws SoldOutException {
        for (ItemNoAndStock itemNoAndStock : itemNoAndStockList) {
            Item findItem = itemRepository.findById(itemNoAndStock.getItemNo()).orElseThrow();
            log.info("item 조회 성공 = {}", findItem.getItemNo());
            int stock = 0;
            try {
                log.info("재고 차감 요청 재고={}", findItem.getStock());
                log.info("현재 재고 ={}", itemNoAndStock.getQuantity());
                stock = findItem.stockDeduction(itemNoAndStock.getQuantity());
                findItem.setStock(stock);
                log.info("재고 차감 성공 후 재고={}", findItem.getStock());
            } catch (SoldOutException e) {
                log.info("재고 부족 [상품번호]={} [현재재고]={}, [차감후 재고]={}",
                        findItem.getItemNo(), findItem.getStock(), stock);
                throw new SoldOutException(e.getMessage());
            }
        }
    }

    private List<ItemNoAndStock> itemNoAndStocks(String sessionId) {
        return cartSearcherRepository.findBySessionId(sessionId)
                .stream()
                .collect(Collectors.groupingByConcurrent(Cart::getItemNo))
                .entrySet().stream()
                .map(entry -> {
                    return new ItemNoAndStock(
                            entry.getKey(),
                            entry.getValue().stream()
                                    .map(Cart::getQuantity)
                                    .reduce(0, Integer::sum));
                })
                .collect(Collectors.toList());
    }

    @Getter
    static class ItemNoAndStock {
        private Long itemNo;
        private int quantity;

        public ItemNoAndStock(Long itemNo, int quantity) {
            this.itemNo = itemNo;
            this.quantity = quantity;
        }
    }
}
