package kr.co._29cm.homework.core.cart.command;

import kr.co._29cm.homework.TestSupplier;
import kr.co._29cm.homework.core.cart.Cart;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static kr.co._29cm.homework.core.fixture.TestFixture._카트_캠핑덕;

@SpringBootTest
@DisplayName("CartCreator class")
class CartCreatorTest extends TestSupplier {

    @Nested
    @DisplayName("create 메서드는")
    class Describe_create_method {

        @Nested
        @DisplayName("카트 등록 명령이 주어지면")
        class Context_request_save {

            @Test
            @DisplayName("카트 정보를 등록해 리턴한다")
            void it_returns_created_cart() {
                String givenSessionId = UUID.randomUUID().toString();
                final Cart savedCart = getCartCreator().create(_카트_캠핑덕(givenSessionId));
                Assertions.assertThat(savedCart).isNotNull();
                Assertions.assertThat(savedCart.getCartId()).isNotNull();
            }
        }
    }

}