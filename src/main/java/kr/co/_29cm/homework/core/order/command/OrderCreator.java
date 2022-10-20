package kr.co._29cm.homework.core.order.command;

import kr.co._29cm.homework.core.cart.command.CartDeleter;
import kr.co._29cm.homework.core.item.command.ItemUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class OrderCreator {
    private final ItemUpdater itemUpdater;
    private final CartDeleter cartDeleter;

    /**
     * Item 에서 주문수량 만큼  재고 차감 -> 재고수량 체크
     * Cart 삭제
     * @param sessionId 카트세션아이디
     */
    @Transactional
    public void create(String sessionId) {
        itemUpdater.update(sessionId);
        cartDeleter.deleteBySessionId(sessionId);
    }
}
