package kr.co._29cm.homework.core.csv;

import kr.co._29cm.homework.core.item.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvCreator {

    private final ResourceLoader resourceLoader;

    public List<Item> loadItem(File csvFile) {

        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();
        Path path = Paths.get(csvFile.toURI());
        CSVParser csvParser;
        List<Item> itemList = new ArrayList<>();
        try {
            log.info("csv 파일을 가져옵니다.");
            csvParser = CSVParser.parse(path, StandardCharsets.UTF_8, csvFormat);
            for(CSVRecord record: csvParser) {
                itemList.add(Item.builder()
                                .itemNo(Long.parseLong(record.get("상품번호")))
                                .itemName(record.get("상품명"))
                                .price(Integer.parseInt(record.get("판매가격")))
                                .stock(Integer.parseInt(record.get("재고수량")))
                        .build());
            }
        } catch (IOException e) {
            log.error("처리할 수 없는 csv 파일입니다.", e.getMessage());
        }
        return itemList;
    }

    public  File getCsvFile(String filename)  {
        Resource resource = resourceLoader.getResource(filename);
        File csvFile;
        try {
            csvFile = resource.getFile();
        } catch (IOException e) {
            throw new NotFoundCsvFileException("존재 하지 않는 csv 파일입니다.");
        }
        return csvFile;
    }
}
