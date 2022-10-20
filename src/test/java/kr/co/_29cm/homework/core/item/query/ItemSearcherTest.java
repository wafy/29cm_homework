package kr.co._29cm.homework.core.item.query;

import kr.co._29cm.homework.TestSupplier;
import kr.co._29cm.homework.core.fixture.TestFixture;
import kr.co._29cm.homework.core.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static kr.co._29cm.homework.core.fixture.TestFixture._디오디너리_데일리_세트_상품번호;
import static kr.co._29cm.homework.core.fixture.TestFixture._캠핑덕_우드롤테이블_상품번호;

@DisplayName("ItemSearcher class")
@SpringBootTest
class ItemSearcherTest extends TestSupplier {

    @BeforeEach
    void init() {
        getItemCreator().create();
    }

    @Nested
    @DisplayName("findAll 메서드는")
    class Describe_findAll_method {

        @Nested
        @DisplayName("조회 명령 이면")
        class Context_request_find {

            @Test
            @DisplayName("등록된 데이터 19개 전체를 반환한다")
            void it_return_all_data() {
                List<Item> result = getItemSearcher().findAll();
                Assertions.assertThat(result.size()).isEqualTo(19);
            }
        }
    }

    @Nested
    @DisplayName("findById 메서드는")
    class Describe_findById_method {

        @Nested
        @DisplayName("존재하는 itemNo로 조회 명령 이면")
        class Context_request_itemNo {

            @Test
            @DisplayName("itemNo에 해당되는 데이터를 리턴한다")
            void it_returns_item() {
                Item result = getItemSearcher().findByItemNo(_캠핑덕_우드롤테이블_상품번호);
                Assertions.assertThat(result).isNotNull();
            }
        }
    }


    @Nested
    @DisplayName("findById 메서드는")
    class Describe_findById_in_method {

        @Nested
        @DisplayName("배열로 itemNos로 조회 명령 이면")
        class Context_request_itemNos {

            @Test
            @DisplayName("itemNo에 해당되는 데이터를 리턴한다")
            void it_returns_items() {
                List<Long> givenItemNos = new ArrayList<>();
                givenItemNos.add(_캠핑덕_우드롤테이블_상품번호);
                givenItemNos.add(_디오디너리_데일리_세트_상품번호);
                List<Item> resultList = getItemSearcher().findByItemNo(givenItemNos);
                Assertions.assertThat(resultList.size()).isEqualTo(2);

            }
        }
    }

}