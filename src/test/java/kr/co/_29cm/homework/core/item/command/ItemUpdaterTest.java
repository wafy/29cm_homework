package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.TestSupplier;
import kr.co._29cm.homework.core.cart.Cart;
import kr.co._29cm.homework.core.item.Item;
import kr.co._29cm.homework.core.item.SoldOutException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static kr.co._29cm.homework.core.fixture.TestFixture._카트_캠핑덕;

@DisplayName("ItemUpdate class")
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ItemUpdaterTest extends TestSupplier {

    @Nested
    @DisplayName("update 메서드는")
    class Describe_update_method {

        @BeforeEach
        void init() {
            getItemCreator().create();
        }

        @Nested
        @DisplayName("Cart sessionId에 해당하는 Item 재고 차감 요청이면")
        class Context_request_sessionId {

            @Nested
            @DisplayName("주문수량(3) 보다 재고 (7) 가 큰 경우이면")
            class Context_request_success {

                String givenSessionId = UUID.randomUUID().toString();

                @BeforeEach
                void init() {
                    getCartCreator().create(_카트_캠핑덕(givenSessionId, 2));
                    getCartCreator().create(_카트_캠핑덕(givenSessionId, 1));
                }

                @Test
                @DisplayName("주문수량만큼 재고를 차감한다")
                void it_returns_void() throws SoldOutException {
                    getItemUpdater().update(givenSessionId);
                }

                @AfterEach
                void after() {
                    getCartDeleter().deleteBySessionId(givenSessionId);
                }
            }

            @Nested
            @DisplayName("주문수량(8) 보다 재고(7) 가 작을 경우이면")
            class Context_request_fail {
                String givenSessionId = UUID.randomUUID().toString();

                @BeforeEach
                void init() {
                    getCartCreator().create(_카트_캠핑덕(givenSessionId, 8));
                }

                @Test
                @DisplayName("SoldOutException 예외를 던진다")
                void it_throws_exception() throws SoldOutException {
                    Assertions.assertThatThrownBy(() ->
                            getItemUpdater().update(givenSessionId)).isInstanceOf(SoldOutException.class);
                }

                @AfterEach
                void after() {
                    getCartDeleter().deleteBySessionId(givenSessionId);
                }
            }

            @Nested
            @DisplayName("[경계 테스트] 주문수량(7) 재고(7) 같은 경우이면")
            class Context_request_fail2 {
                String givenSessionId = UUID.randomUUID().toString();
                Cart cart;

                @BeforeEach
                void init() {
                    cart = getCartCreator().create(_카트_캠핑덕(givenSessionId, 7));
                }

                @Test
                @DisplayName("주문수량 만큼 재고를 차감한다")
                void it_returns() throws SoldOutException {
                    getItemUpdater().update(givenSessionId);
                    Item resultItem = getItemSearcher().findByItemNo(cart.getItemNo());
                    Assertions.assertThat(resultItem.getStock()).isEqualTo(0);
                }

                @AfterEach
                void after() {
                    getCartDeleter().deleteBySessionId(givenSessionId);
                }
            }
        }
    }
}