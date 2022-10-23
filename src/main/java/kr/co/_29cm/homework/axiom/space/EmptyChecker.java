package kr.co._29cm.homework.axiom.space;

/**
 * 스페이스를 체크하여 TURE, FALSE 를 리턴합니다.
 */
public class EmptyChecker {

    public static boolean isEmptyCheck(String input) {
        return input == null || input.trim().isEmpty();
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
