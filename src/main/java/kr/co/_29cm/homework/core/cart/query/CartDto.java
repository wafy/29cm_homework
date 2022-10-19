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

    public CartDto(List<Cart> cartList, int orderAmount, int paymentAmount) {
        this.cartList = cartList;
        this.orderAmount = orderAmount;
        this.paymentAmount = paymentAmount;
    }


}
