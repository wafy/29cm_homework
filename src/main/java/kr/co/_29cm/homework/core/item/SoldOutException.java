package kr.co._29cm.homework.core.item;

/**
 * 재고가 부족할 경우 결제를 시도할 시 발생.
 */
public class SoldOutException extends RuntimeException {

    public SoldOutException(String message) {
        super(message);
    }
}
