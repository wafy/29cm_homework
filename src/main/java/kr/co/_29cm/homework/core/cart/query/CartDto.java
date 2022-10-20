package kr.co._29cm.homework.core.cart.query;

import kr.co._29cm.homework.core.cart.Cart;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode
public class CartDto {

    private final List<Cart> cartList;
    private final int orderAmount;
    private final int paymentAmount;
    private final int deliveryCharge;

    public CartDto(List<Cart> cartList, int orderAmount, int paymentAmount, int deliveryCharge) {
        this.cartList = cartList;
        this.orderAmount = orderAmount;
        this.paymentAmount = paymentAmount;
        this.deliveryCharge = deliveryCharge;
    }


}
