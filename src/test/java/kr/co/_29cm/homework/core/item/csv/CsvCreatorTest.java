package kr.co._29cm.homework.core.item.csv;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@DisplayName("CsvCreator class")
@SpringBootTest
class CsvCreatorTest {

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
                String _존재하는_csv_filename = "items_data.csv";
                File csvFile = csvCreator.getCsvFile(_존재하는_csv_filename);
                Assertions.assertThat(csvFile).isFile();
            }
        }

        @Nested
        @DisplayName("존재하지 않는 csv 파일이 주어지면")
        class Context_none_exist_csv {
            String _존재하지_않는_csv_filename = "존재하지 않는 파일명.csv";
            @Test
            @DisplayName("NotFoundCsvFileException 을 던진다")
            void it_throws_exception() {
                Assertions.assertThatThrownBy(() ->
                        csvCreator.getCsvFile(_존재하지_않는_csv_filename)).isInstanceOf(NotFoundCsvFileException.class);
            }
        }
    }

}