package kr.co._29cm.homework.axiom.enums;

public enum InputType {

    ORDER, O, QUIT, Q;

    public static boolean isOrder(final String param) {
        InputType input = InputType.valueOf(param.toUpperCase());
        return InputType.O == input || InputType.ORDER == input;
    }

    public static boolean isQuit(final String param) {
        InputType input = InputType.valueOf(param.toUpperCase());
        return InputType.Q == input || InputType.QUIT == input;
    }

}
