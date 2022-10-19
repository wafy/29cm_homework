package kr.co._29cm.homework.core.item.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ItemNoAndStock {

    private Long itemNo;
    private int quantity;

    public ItemNoAndStock(Long itemNo, int quantity) {
        this.itemNo = itemNo;
        this.quantity = quantity;
    }
}
