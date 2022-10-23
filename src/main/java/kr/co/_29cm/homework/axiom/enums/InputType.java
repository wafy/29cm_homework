package kr.co._29cm.homework.axiom.enums;

/**
 * 주문과 종료의 형식을 정의합니다.
 */
public enum InputType {

    ORDER, O, QUIT, Q;

    /**
     * 입력(주문)의 유효성을 체크합니다.
     * @param param 입력받은 문자열
     * @return 유효성 여부
     */
    public static boolean isOrder(final String param) {
        InputType input = InputType.valueOf(param.toUpperCase());
        return InputType.O == input || InputType.ORDER == input;
    }

    /**
     * 종료의 유효성을 체크합니다.
     * @param param 입력받은 문자열
     * @return 유효성 여부
     */
    public static boolean isQuit(final String param) {
        InputType input = InputType.valueOf(param.toUpperCase());
        return InputType.Q == input || InputType.QUIT == input;
    }

}
