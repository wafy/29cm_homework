package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.core.csv.CsvCreator;
import kr.co._29cm.homework.core.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCreator {
    private final ItemRepository itemRepository;
    private final CsvCreator csvCreator;

    private static final String CSV_FILE_NAME = "items_data.csv";

    public void create() {
        List<Item> itemList = loadCsvItem();
        itemRepository.saveAll(itemList);
    }

    private List<Item> loadCsvItem() {
        File csvFile = csvCreator.getCsvFile(CSV_FILE_NAME);
        return csvCreator.loadItem(csvFile);
    }
}
