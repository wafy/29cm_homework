package kr.co._29cm.homework.core.cart.query;

import kr.co._29cm.homework.consts.MyConstants;
import kr.co._29cm.homework.core.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static kr.co._29cm.homework.core.cart.query.DeliveryCharge.getDeliveryCharge;

@Service
@RequiredArgsConstructor
public class CartSearcher {

    private final CartSearcherRepository searcherRepository;

    public List<Cart> findBySessionId(String sessionId) {
        return searcherRepository.findBySessionId(sessionId);
    }

    /**
     * 주문금액 : 카트에 담긴 상품금액 + 주문 갯수
     * 배송비 : 주문금액 < 5만원 일 경우 2500
     * 결제금액 : 주문금액 + 배송비
     *
     * @param sessionId 세션 아이디
     */
    public CartDto totalCart(String sessionId) {
        // 카트에 담긴 상품 목록
        List<Cart> cartList = findBySessionId(sessionId);

        // 주문 금액
        int orderAmount = cartList.stream()
                .map(d -> d.getPrice() * d.getQuantity())
                .reduce(0, Integer::sum);

        // 배송비
        int charge = getDeliveryCharge(orderAmount);
        // 결제 지금액 (주문금액 + 배송비)
        int paymentAmount = orderAmount + MyConstants.DELIVERY_CHARGE;

        return new CartDto(cartList, orderAmount, paymentAmount, charge);
    }
}
