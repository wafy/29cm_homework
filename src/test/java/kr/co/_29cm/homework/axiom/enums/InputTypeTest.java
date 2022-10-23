package kr.co._29cm.homework.axiom.enums;

import kr.co._29cm.homework.axiom.enums.InputType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("InputType class")
class InputTypeTest {

    @Nested
    @DisplayName("isOrder 메서드는")
    class Describe_isOrder_method {

        @Nested
        @DisplayName("enum 에 정의된 inputType 주문 값 요청이면")
        class Context_request_inputype {

            @ParameterizedTest()
            @ValueSource(strings = {
                    "o",
                    "O",
                    "order",
                    "ORDER"
            })
            @DisplayName("true 를 리턴한다")
            void it_returns_true(String input) {
                boolean result = InputType.isOrder(input);
                Assertions.assertThat(result).isTrue();
            }
        }
    }

    @Nested
    @DisplayName("isQuit 메서드는")
    class Describe_isQuit_method {

        @Nested
        @DisplayName("enum 에 정의된 inputType 값 종료 요청이면")
        class Context_request_inputype {

            @ParameterizedTest()
            @ValueSource(strings = {
                    "q",
                    "Q",
                    "quit",
                    "QUIT"
            })
            @DisplayName("true 를 리턴한다")
            void it_returns_true(String input) {
                boolean result = InputType.isQuit(input);
                Assertions.assertThat(result).isTrue();
            }
        }
    }


}