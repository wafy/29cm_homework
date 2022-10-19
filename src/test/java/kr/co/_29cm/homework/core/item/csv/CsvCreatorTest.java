package kr.co._29cm.homework.core.item.csv;

import kr.co._29cm.homework.core.csv.CsvCreator;
import kr.co._29cm.homework.core.csv.NotFoundCsvFileException;
import kr.co._29cm.homework.core.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@DisplayName("CsvCreator class")
@SpringBootTest
class CsvCreatorTest {

    String _존재하는_csv_filename = "items_data.csv";
    String _존재하지_않는_csv_filename = "존재하지 않는 파일명.csv";

    @Autowired
    private CsvCreator csvCreator;

    @Nested
    @DisplayName("getCsvFile 메서드는")
    class Describe_getCsvFile_method {

        @Nested
        @DisplayName("존재하는 csv 파일이 주어지면")
        class Context_exist_csv {

            @Test
            @DisplayName("csv 파일을 반환한다")
            void it_returns_csv_file() {
                File csvFile = csvCreator.getCsvFile(_존재하는_csv_filename);
                Assertions.assertThat(csvFile).isFile();
            }
        }

        @Nested
        @DisplayName("존재하지 않는 csv 파일이 주어지면")
        class Context_none_exist_csv {

            @Test
            @DisplayName("NotFoundCsvFileException 을 던진다")
            void it_throws_exception() {
                Assertions.assertThatThrownBy(() ->
                        csvCreator.getCsvFile(_존재하지_않는_csv_filename)).isInstanceOf(NotFoundCsvFileException.class);
            }
        }
    }

    @Nested
    @DisplayName("loadItem 메서드는")
    class Describe_loadItem_method {

        @Nested
        @DisplayName("성공적으로 csv 이 읽혀지면")
        class Context_read_csv_success {

            @Test
            @DisplayName("사이즈가 19인 List<Item>을 반환한다")
            void it_returns_items() {
                File file = csvCreator.getCsvFile(_존재하는_csv_filename);
                List<Item> items = csvCreator.loadItem(file);
                Assertions.assertThat(items.size()).isEqualTo(19);
            }
        }

    }

}