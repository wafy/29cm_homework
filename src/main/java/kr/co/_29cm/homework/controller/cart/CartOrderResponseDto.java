package kr.co._29cm.homework.controller.cart;

import kr.co._29cm.homework.core.cart.query.CartDto;
import lombok.Getter;

import java.text.DecimalFormat;

@Getter
public class CartOrderResponseDto {
    private final int orderAmount;
    private final int deliveryCharge;
    private final int paymentAmount;

    public CartOrderResponseDto(CartDto cartDto) {
        this.orderAmount =cartDto.getOrderAmount();
        this.deliveryCharge = cartDto.getDeliveryCharge();
        this.paymentAmount = cartDto.getPaymentAmount();
    }



    public static String displayCurrency(int amount) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(amount);
    }
}
