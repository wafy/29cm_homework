package kr.co._29cm.homework.controller;

import kr.co._29cm.homework.axiom.number.NumberChecker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("NumberChecker class")
class NumberCheckerTest {

    @Nested
    @DisplayName("isNumber 메서드는")
    class Describe_isNumber_method {

        @Nested
        @DisplayName("주어진 문자열이 숫자이면")
        class Context_request_number {

            @ParameterizedTest
            @ValueSource(strings = {
                    "1",
                    "2",
                    "3",
                    "234556",
                    "98767554",
                    "0"
            })
            @DisplayName("true를 리턴한다")
            void it_returns_true(String input) {
                boolean result = NumberChecker.isNumber(input);
                Assertions.assertThat(result).isTrue();
            }
        }

        @Nested
        @DisplayName("주어진 문자열이 숫자가 아니면")
        class Context_request_no_number {

            @ParameterizedTest
            @ValueSource(strings = {
                    "1AAAD",
                    "DDD2",
                    "!!@3",
                    " 2344FFF",
                    "98767554A ",
                    "0^"
            })
            @DisplayName("false 를 리턴한다")
            void it_returns_true(String input) {
                boolean result = NumberChecker.isNumber(input);
                Assertions.assertThat(result).isFalse();
            }
        }
    }

}