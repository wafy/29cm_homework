package kr.co._29cm.homework.consts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;

@DisplayName("InputType class")
class InputTypeTest {

    @Nested
    @DisplayName("isNotContains 메서드는")
    class Describe_isNotContains_method {

        @Nested
        @DisplayName("enum 에 정의된 inputType 값 요청이면")
        class Context_request_inputype {

            @ParameterizedTest()
            @ValueSource(strings = {
                    "q",
                    "Q",
                    "o",
                    "O"
            })
            @DisplayName("true 를 리턴한다")
            void it_returns_true(String input) {
                boolean result = InputType.isNotContains(input);
                Assertions.assertThat(result).isTrue();
            }
        }


        @Nested
        @DisplayName("enum 에 정의되지 inputType 값 요청이면")
        class Context_request_none_inputype {

            @ParameterizedTest()
            @ValueSource(strings = {
                    "q1",
                    "11334",
                    "XXXXX",
                    "~!@##$%",
                    "09876553",
                    "0",
                    "Q1",
                    "q1",
                    "o4",
                    "O5"
            })
            @DisplayName("false 를 리턴한다")
            void it_returns_true(String input) {
                boolean result = InputType.isNotContains(input);
                Assertions.assertThat(result).isFalse();
            }
        }
    }


}