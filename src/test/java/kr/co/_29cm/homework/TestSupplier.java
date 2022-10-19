package kr.co._29cm.homework;

import kr.co._29cm.homework.axiom.target.ForTestOnly;
import kr.co._29cm.homework.core.item.command.ItemCreator;
import kr.co._29cm.homework.core.item.command.ItemRepository;
import kr.co._29cm.homework.core.csv.CsvCreator;
import kr.co._29cm.homework.core.item.query.ItemSearcher;
import kr.co._29cm.homework.core.item.query.ItemSearcherRepository;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

public abstract class TestSupplier implements ForTestOnly {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    ResourceLoader resourceLoader;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private ItemRepository itemRepository;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private ItemSearcherRepository itemSearcherRepository;

    private CsvCreator csvCreator;
    private ItemCreator itemCreator;
    private ItemSearcher itemSearcher;


    protected CsvCreator getCsvCreator() {
        return csvCreator == null ? new CsvCreator(resourceLoader) : csvCreator;
    }

    protected ItemCreator getItemCreator() {
        return itemCreator == null ? new ItemCreator(itemRepository, getCsvCreator()) : itemCreator;
    }

    protected ItemSearcher getItemSearcher() {
        return itemSearcher == null ? new ItemSearcher(itemSearcherRepository) : itemSearcher;
    }
}
