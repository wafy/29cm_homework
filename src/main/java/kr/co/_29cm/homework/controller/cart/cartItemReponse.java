package kr.co._29cm.homework.controller.cart;

import kr.co._29cm.homework.core.cart.Cart;
import lombok.Getter;

@Getter
public class cartItemReponse {

    private String itemName;
    private int quantity;

    public cartItemReponse(Cart cart) {
        this.itemName = cart.getItemName();
        this.quantity = cart.getQuantity();
    }

    @Override
    public String toString() {
        return itemName + " - " + quantity+"개";
    }
}
