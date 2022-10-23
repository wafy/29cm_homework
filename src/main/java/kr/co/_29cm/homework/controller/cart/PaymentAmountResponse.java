package kr.co._29cm.homework.controller.cart;

import kr.co._29cm.homework.core.cart.query.CartDto;
import lombok.Getter;

import java.text.DecimalFormat;

/**
 * 주문금액, 배송비, 결제금액을 응답해줍니다.
 */
@Getter
public class PaymentAmountResponse {
    private final int orderAmount;
    private final int deliveryCharge;
    private final int paymentAmount;

    public PaymentAmountResponse(CartDto cartDto) {
        this.orderAmount = cartDto.getOrderAmount();
        this.deliveryCharge = cartDto.getDeliveryCharge();
        this.paymentAmount = cartDto.getPaymentAmount();
    }


    /**
     * 숫자에 세자리마다 콤마를 표시합니다.
     * @param amount 금액
     * @return 세자리미다 콤마가 적용된 문자열
     */
    public static String displayCurrency(int amount) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(amount);
    }
}
