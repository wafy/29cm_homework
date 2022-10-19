package kr.co._29cm.homework.core.order.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderCreator {
    private final OrderRepository orderRepository;

    /**
     * Item 에서 주문수량 만큼  재고 차감 -> 재고수량 체크
     * Cart 삭제
     * Order 등록
     * @param sessionId 카트세션아이디
     */
    public void create(String sessionId) {
        //item 에서 재고 차감

        //

    }
}
