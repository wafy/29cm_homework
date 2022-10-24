package kr.co._29cm.homework.core.item;

import lombok.Getter;

/**
 * 재고가 부족할 경우 결제를 시도할 시 발생.
 */
@Getter
public class SoldOutException extends RuntimeException {

    private String itemNo;
    private String quantity;

    public SoldOutException(String message, Long itemNo, int quantity) {
        super(message);
        this.itemNo = String.valueOf(itemNo);
        this.quantity = String.valueOf(quantity);
    }
    public SoldOutException(String message) {
        super(message);
    }
}
