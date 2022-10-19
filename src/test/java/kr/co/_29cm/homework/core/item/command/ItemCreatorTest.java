package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.TestSupplier;
import kr.co._29cm.homework.core.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@DisplayName("ItemCreator class")
@SpringBootTest
class ItemCreatorTest extends TestSupplier {

    @Nested
    @DisplayName("create 메서드는")
    class Describe_create_method {

        @Nested
        @DisplayName("정상적으로 등록 처리가 되면")
        class Context_create_success {

            @Test
            @DisplayName("상품 데이터가 데이타베이스에 등록 된다")
            void it_returns_saved_items() {
                getItemCreator().create();
                List<Item> result = getItemSearcher().findAll();
                Assertions.assertThat(result).isNotNull();
            }
        }
    }
}