package kr.co._29cm.homework.axiom.order;

import static kr.co._29cm.homework.axiom.space.EmptyChecker.isEmptyCheck;

/**
 * 상품번호와 수량의 유효성을 체크합니다.
 */
public class InputCommandChecker {

    /**
     *  1. 상품번호 스페이스, 수량 스페이슬 : false
     *  2. 상품번호 입력, 수량 스페이스 : false
     *  2. 상품번호 입력, 수량 입력 : true
     * @param itemNo 상품번호
     * @param quantity 수량
     * @return 유효할경우 true
     */
    public static boolean isValidOrder(String itemNo, String quantity) {
        if (!isEmptyCheck(itemNo) && !isEmptyCheck(quantity)) {
            return true;
        } else if (!isEmptyCheck(itemNo) && isEmptyCheck(quantity)) {
            return false;
        }
        return false;
    }

    /**
     * 상품번호 스페이스, 수량 스페이스 true
     * 그외 false
     * @param itemNo 상품번호
     * @param quantity 수량
     * @return 유효할 경우 true
     */
    public static boolean isEmptyOrder(String itemNo, String quantity) {
        return (isEmptyCheck(itemNo) && isEmptyCheck(quantity));
    }
}
