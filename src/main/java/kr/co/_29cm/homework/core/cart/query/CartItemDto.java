package kr.co._29cm.homework.core.cart.query;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class CartItemDto {

    private Long itemNo;
    private String itemName;
    private int quantity;

    public CartItemDto(Long itemNo, String itemName, int quantity) {
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.quantity = quantity;
    }
}
