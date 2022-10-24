package kr.co._29cm.homework.axiom.enums;

/**
 * 주문과 종료의 형식을 정의합니다.
 */
public enum CommandType {

    ORDER, O, QUIT, Q;

    /**
     * 입력(주문)의 유효성을 체크합니다.
     * @param param 입력받은 문자열
     * @return 유효성 여부
     */
    public static boolean isOrder(final String param) {
        CommandType input;
        try {
            input = CommandType.valueOf(param.toUpperCase());
        } catch (IllegalArgumentException e) {
           throw new IllegalArgumentException("명령어를 다시한번 확인해주세요.");
        }
        return CommandType.O == input || CommandType.ORDER == input;
    }

    /**
     * 종료의 유효성을 체크합니다.
     * @param param 입력받은 문자열
     * @return 유효성 여부
     */
    public static boolean isQuit(final String param) {
        CommandType input;
        try {
            input = CommandType.valueOf(param.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("명령어를 다시한번 확인해주세요.");
        }
        return CommandType.Q == input || CommandType.QUIT == input;
    }

}
