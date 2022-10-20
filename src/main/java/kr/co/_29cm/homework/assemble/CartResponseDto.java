package kr.co._29cm.homework.assemble;

import kr.co._29cm.homework.core.cart.Cart;
import lombok.Getter;

@Getter
public class CartResponseDto {

    private String itemName;
    private int quantity;

    public CartResponseDto(Cart cart) {
        this.itemName = cart.getItemName();
        this.quantity = cart.getQuantity();
    }

    @Override
    public String toString() {
        return itemName + "= " + quantity +'\'';
    }
}
