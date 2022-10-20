package kr.co._29cm.homework.core.cart.query;

import kr.co._29cm.homework.consts.MyConstants;

public class DeliveryCharge {

    /**
     * 주문금액이 5만원 이하일 경우 배송비 추가 여부를 반환한다.
     */
    public static int getDeliveryCharge(int orderAmount) {
        return orderAmount < 50000 ?  0 :  MyConstants.DELIVERY_CHARGE;
    }
}
