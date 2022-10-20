package kr.co._29cm.homework.core.cart.command;

import kr.co._29cm.homework.TestSupplier;
import kr.co._29cm.homework.core.cart.Cart;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static kr.co._29cm.homework.core.fixture.TestFixture._카트_캠핑덕;

@DisplayName("CartDelete class")
@SpringBootTest
class CartDeleterTest extends TestSupplier {

    @Nested
    @DisplayName("deleteBySessionId 메서드")
    class Describe_deleteBySessionId_method {

        @Nested
        @DisplayName("sessionId로 카트 삭제 요청이면")
        class Context_request_sessionId {
            String givenSessionId;
            Cart cart;
            @BeforeEach
            void init() {
                givenSessionId = UUID.randomUUID().toString();
                cart = getCartCreator().add(_카트_캠핑덕(givenSessionId));
            }

            @Test
            @DisplayName("sessionId에 해당하는 카트데이터를 삭제하고 1을 리턴한다")
            void it_delete() {
                long result = getCartDeleter().deleteBySessionId(givenSessionId);
                Assertions.assertThat(result).isEqualTo(1);
            }
        }

        @Nested
        @DisplayName("존재하지 않는 sessionId 카트 삭제 요청이면")
        class Context_none_exist_sessionId {

            @Test
            @DisplayName("삭제를 처리하지 못하고 0을 리턴한다")
            void it_none_delete() {
                String noneExistSessionId = "XXXXXXX";
                long result = getCartDeleter().deleteBySessionId(noneExistSessionId);
                Assertions.assertThat(result).isEqualTo(0);
            }
        }
    }


}