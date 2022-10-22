package kr.co._29cm.homework.core.cart.query;

/**
 * 배송비 채정을 괸리합니다.
 */
public class DeliveryCharge {

    private static final int DELIVERY_CHARGE = 2500; //배송비
    private static final int DELIVERY_BOUNDARY = 50000; // 배송비 부과 기준

    /**
     * 주문금액이 5만원 이하일 경우 배송비 추가 여부를 반환한다.
     */
    public static int getDeliveryCharge(int orderAmount) {
        return orderAmount >= DELIVERY_BOUNDARY ?  0 :  DELIVERY_CHARGE;
    }
}
