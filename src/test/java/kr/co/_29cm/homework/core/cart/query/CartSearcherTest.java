package kr.co._29cm.homework.core.cart.query;

import kr.co._29cm.homework.TestSupplier;
import kr.co._29cm.homework.core.cart.Cart;
import kr.co._29cm.homework.core.fixture.TestFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static kr.co._29cm.homework.core.fixture.TestFixture._초콜릿;
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

            @AfterEach
            void after() {
                cartDeleteAll();
                itemDeleteAll();
            }
        }
    }

    @Nested
    @DisplayName("totalCart 메서드")
    class Describe_totalCart {

        @Nested
        @DisplayName("3000원 상품 2개와 45_000 상품 1개가 장바구니에 담긴 요청이면")
        class Context_request_order {

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
            @DisplayName("카트에 담긴 상품 목록과 51000원이 계산된 결제금액이 리턴된다")
            void it_returns_total_cart() {
                CartDto cartDto = getCartSearcher().totalCart(givenSessionId);
                Assertions.assertThat(cartDto.getCartList().size()).isEqualTo(3);
                Assertions.assertThat(cartDto.getPaymentAmount()).isEqualTo(51000);
            }

            @AfterEach
            void after() {
                cartDeleteAll();
                itemDeleteAll();
            }
        }

        @DisplayName("3000원 상품 2개와 장바구니에 담긴 요청이면")
        class Context_request_two_item {

            String givenSessionId;

            @BeforeEach
            void init() {
                givenSessionId = UUID.randomUUID().toString();
                getItemCreator().create();
                getCartCreator().create(_카트_디오디너리(givenSessionId));
                getCartCreator().create(_카트_디오디너리(givenSessionId));
            }

            @Test
            @DisplayName("카트에 담긴 상품 목록과 배송비 2500원이 합산된 8500원 결제금액이 리턴된다")
            void it_returns_total_cart() {
                CartDto cartDto = getCartSearcher().totalCart(givenSessionId);
                Assertions.assertThat(cartDto.getCartList().size()).isEqualTo(2);
                Assertions.assertThat(cartDto.getPaymentAmount()).isEqualTo(8500);
            }

            @AfterEach
            void after() {
                cartDeleteAll();
                itemDeleteAll();
            }
        }

        @DisplayName("[경계테스트] 합계가 50000원 상품의 요쳥일 경우")
        class Context_request_equals_delivery_cahrge {

            String givenSessionId;

            @BeforeEach
            void init() {
                givenSessionId = UUID.randomUUID().toString();
                getItemCreator().create();
                getCartCreator().create(_초콜릿(givenSessionId));
                getCartCreator().create(_초콜릿(givenSessionId));
            }

            @Test
            @DisplayName("카트에 담긴 상품 목록과 배송비 2500원이 합산된 8500원 결제금액이 리턴된다")
            void it_returns_total_cart() {
                CartDto cartDto = getCartSearcher().totalCart(givenSessionId);
                Assertions.assertThat(cartDto.getCartList().size()).isEqualTo(2);
                Assertions.assertThat(cartDto.getPaymentAmount()).isEqualTo(52500);
            }

            @AfterEach
            void after() {
                cartDeleteAll();
                itemDeleteAll();
            }
        }
    }

}