package kr.co._29cm.homework.core.item.csv;

import kr.co._29cm.homework.core.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvCreator {

    private final ResourceLoader resourceLoader;
    private static final String CSV_FILE_NAME = "items_data.csv";

    public List<Item> readCsv() {


        return null;
    }

    public File getCsvFile(String filename) {
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
