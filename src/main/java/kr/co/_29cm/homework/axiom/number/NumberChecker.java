package kr.co._29cm.homework.axiom.number;

import java.util.regex.Pattern;

/**
 * 입력받은 문자열이 숫자로만 되어 있는지 체크하여 TURE , FALSE 를 리턴합니다.
 */
public class NumberChecker {

    private static final Pattern PATTERN = Pattern.compile("^[0-9]*$");


    public static boolean isNumber(String input) {
        return PATTERN.matcher(input).find();
    }
}
