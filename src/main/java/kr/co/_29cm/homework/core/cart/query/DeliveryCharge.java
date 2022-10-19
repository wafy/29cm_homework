package kr.co._29cm.homework.core.cart.query;

public class DeliveryCharge {

    private static final int DELIVERY_CHARGE = 2500;

    /**
     * 주문금액이 5만원 이하일 경우 배송비 2500원을 추가한다.
     * @return 배송비가 계산된 주문 금액
     */
    public static int getOrderAmount(int orderAmount) {
        return (orderAmount < 50000 ? orderAmount + DELIVERY_CHARGE : orderAmount);
    }
}
