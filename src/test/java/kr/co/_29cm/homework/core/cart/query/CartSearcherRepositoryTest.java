package kr.co._29cm.homework.core.cart.query;

import kr.co._29cm.homework.TestSupplier;
import kr.co._29cm.homework.core.cart.Cart;
import kr.co._29cm.homework.core.fixture.TestFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static kr.co._29cm.homework.core.fixture.TestFixture._카트_디오디너리;

@DisplayName("CartSearcher class")
@SpringBootTest
class CartSearcherTest extends TestSupplier {

    @Nested
    @DisplayName("findBySessionId 메서드는")
    class Describe_findBySessionId_method {

        @Nested
        @DisplayName("sessionId로 조회 요청이면")
        class Context_request_sessionId {

            String givenSessionId;
            @BeforeEach
            void init() {
                givenSessionId = UUID.randomUUID().toString();
                getItemCreator().create();
                getCartCreator().create(_카트_디오디너리(givenSessionId));
                getCartCreator().create(_카트_디오디너리(givenSessionId));
                getCartCreator().create(TestFixture._카트_캠핑덕(givenSessionId));
            }

            @Test
            @DisplayName("등록된 카드를 리턴한다")
            void it_returns_saved_cart() {
                List<Cart> resultList = getCartSearcher().findBySessionId(givenSessionId);
                Assertions.assertThat(resultList.size()).isEqualTo(3);
            }
        }
    }

}