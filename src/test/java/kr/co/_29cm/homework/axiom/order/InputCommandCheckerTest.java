package kr.co._29cm.homework.axiom.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("InputCommandChecker class")
class InputCommandCheckerTest {

    @Nested
    @DisplayName("isValidOrder 메서드는")
    class Describe_isValidOrder_method {

        @Nested
        @DisplayName("상품번호와 수량이 모두 입력된 요청이면")
        class Context_valid_itemNo_quantity {

            @Test
            @DisplayName("true 를 리턴한다")
            void it_returns_true() {
                String givenItemNo = "1111";
                String givenQuantity = "1";
                boolean result = InputCommandChecker.isValidOrder(givenItemNo, givenQuantity);
                Assertions.assertThat(result).isTrue();

            }
        }
    }

}