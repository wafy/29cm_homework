package kr.co._29cm.homework.core.item.query;

import kr.co._29cm.homework.TestSupplier;
import kr.co._29cm.homework.core.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@DisplayName("ItemSearcher class")
@SpringBootTest
class ItemSearcherTest extends TestSupplier {

    @Nested
    @DisplayName("findAll 메서드는")
    class Describe_findAll_method {

        @Nested
        @DisplayName("조회 요청이면")
        class Context_request_find {

            @BeforeEach
            void init() {
                getItemCreator().create();
            }

            @Test
            @DisplayName("등록된 데이터 19개 전체를 반환한다")
            void it_return_all_data() {
                List<Item> result = getItemSearcher().findAll();
                Assertions.assertThat(result.size()).isEqualTo(19);
            }
        }
    }

}