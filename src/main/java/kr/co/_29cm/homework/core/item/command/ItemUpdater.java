package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.core.cart.Cart;
import kr.co._29cm.homework.core.cart.query.CartSearcherRepository;
import kr.co._29cm.homework.core.item.SoldOutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ItemUpdater {
    private final CartSearcherRepository cartSearcherRepository;
    private final ItemUpdateStock itemUpdateStock;

    public void update(String sessionId) throws SoldOutException {
        log.info("itemNo와 quantity 조회");
        List<ItemNoAndStock> itemNoAndStockList = itemNoAndStocks(sessionId);
        itemUpdateStock.updateStock(itemNoAndStockList);
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


}
