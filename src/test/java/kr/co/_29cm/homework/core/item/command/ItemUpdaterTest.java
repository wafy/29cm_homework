package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.TestSupplier;
import kr.co._29cm.homework.core.item.SoldOutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static kr.co._29cm.homework.core.fixture.TestFixture._카트_캠핑덕;

@DisplayName("ItemUpdate class")
@SpringBootTest
class ItemUpdaterTest extends TestSupplier {

    @Nested
    @DisplayName("update 메서드는")
    class Describe_update_method {

        @Nested
        @DisplayName("Cart sessionId에 해당하는 Item 재고 차감 요칭이면")
        class Context_request_sessionId {

            @Nested
            @DisplayName("주문수량(3) 보다 재고 (7) 가 큰 경우이면")
            class Context_request_success {

                String givenSessionId = UUID.randomUUID().toString();

                @BeforeEach
                void init() {
                    getItemCreator().create();
                    getCartCreator().create(_카트_캠핑덕(givenSessionId, 2));
                    getCartCreator().create(_카트_캠핑덕(givenSessionId, 1));
                }

                @Test
                @DisplayName("주문수량만큼 재고를 차감한다")
                void it_returns_void() throws SoldOutException {
                    getItemUpdater().update(givenSessionId);
                }
            }
        }
    }


}