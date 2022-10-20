package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.TestSupplier;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.*;

import static kr.co._29cm.homework.core.fixture.TestFixture._카트_캠핑덕;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("ItemUpdate class")
@SpringBootTest
class ItemUpdaterConcurrentTest extends TestSupplier {

    @Nested
    @DisplayName("update 메서드는")
    class Describe_update_method {

        @Nested
        @DisplayName("[동시성 테스트] 여러명의 사용자가 동시에 재고 차감을 요청할 경우")
        class Context_concurrency {

            @Nested
            @DisplayName("재고(7)보다 구매 수량이 클 경우")
            class Context_request_multi_user {


                @BeforeEach
                void init() {
                    getItemCreator().create();
                }

                @Test
                @DisplayName("SoldOutException 을 던진다")
                @Transactional
                void it_throws_exception() throws InterruptedException {
                    int THREAD_COUNT = 8;
                    ExecutorService executorService = Executors.newFixedThreadPool(1);
                    CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

                    Future<?> f = null;
                    for(int i = 0; i < THREAD_COUNT; i++) {
                        f = executorService.submit(() -> {
                            try {
                                String givenSessionId = UUID.randomUUID().toString();
                                log.info("생성된 세션아이디={}", givenSessionId);
                                getCartCreator().add(_카트_캠핑덕(givenSessionId, 1));
                                log.info("재고처리 요청");
                                getItemUpdater().update(givenSessionId);

                            } finally {
                                latch.countDown();
                            }
                        });
                    }
                    latch.await();

                    Future<?> result = f;
                    Assertions.assertThatThrownBy(result::get).isInstanceOf(ExecutionException.class);
                }
            }
        }
    }
}