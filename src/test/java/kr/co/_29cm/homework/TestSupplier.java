package kr.co._29cm.homework;

import kr.co._29cm.homework.axiom.target.ForTestOnly;
import kr.co._29cm.homework.core.cart.command.CartCreator;
import kr.co._29cm.homework.core.cart.command.CartDeleter;
import kr.co._29cm.homework.core.cart.command.CartRepository;
import kr.co._29cm.homework.core.cart.query.CartSearcher;
import kr.co._29cm.homework.core.cart.query.CartSearcherRepository;
import kr.co._29cm.homework.core.csv.CsvCreator;
import kr.co._29cm.homework.core.item.command.ItemCreator;
import kr.co._29cm.homework.core.item.command.ItemRepository;
import kr.co._29cm.homework.core.item.command.ItemUpdateStock;
import kr.co._29cm.homework.core.item.command.ItemUpdater;
import kr.co._29cm.homework.core.item.query.ItemSearcher;
import kr.co._29cm.homework.core.item.query.ItemSearcherRepository;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;



public abstract class TestSupplier implements ForTestOnly {


    @Autowired
    @Getter(AccessLevel.PROTECTED)
    ResourceLoader resourceLoader;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private ItemRepository itemRepository;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private CartRepository cartRepository;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private ItemSearcherRepository itemSearcherRepository;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private CartSearcherRepository cartSearcherRepository;

    private CsvCreator csvCreator;
    private ItemCreator itemCreator;
    private ItemSearcher itemSearcher;
    private CartCreator cartCreator;
    private CartSearcher cartSearcher;
    private CartDeleter cartDeleter;
    private ItemUpdater itemUpdater;
    private ItemUpdateStock itemUpdateStock;


    protected CsvCreator getCsvCreator() {
        return csvCreator == null ? new CsvCreator(resourceLoader) : csvCreator;
    }

    protected ItemCreator getItemCreator() {
        return itemCreator == null ? new ItemCreator(itemRepository, getCsvCreator()) : itemCreator;
    }

    protected ItemSearcher getItemSearcher() {
        return itemSearcher == null ? new ItemSearcher(itemSearcherRepository) : itemSearcher;
    }

    protected CartCreator getCartCreator() {
        return cartCreator == null ? new CartCreator(cartRepository) : cartCreator;
    }

    protected CartSearcher getCartSearcher() {
        return cartSearcher == null ? new CartSearcher(cartSearcherRepository) : cartSearcher;
    }

    protected CartDeleter getCartDeleter() {
        return cartDeleter == null ? new CartDeleter(cartRepository) : cartDeleter;
    }

    protected ItemUpdater getItemUpdater() {
        return itemUpdater == null ? new ItemUpdater(cartSearcherRepository, getItemUpdateStock()) : itemUpdater;
    }

    protected ItemUpdateStock getItemUpdateStock() {
        return itemUpdateStock == null ? new ItemUpdateStock(itemRepository, getItemSearcher()) : itemUpdateStock;
    }

    protected void itemDeleteAll() {
        itemRepository.deleteAll();
    }

    protected void cartDeleteAll() {
        cartRepository.deleteAll();
    }



}
