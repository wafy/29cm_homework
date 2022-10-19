package kr.co._29cm.homework.core.item;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "item")
public class Item {

    @Id
    private Long itemNo;
    private String itemName;
    private int price;
    private int stock;

    /**
     * jpa가 필요로 합니다.
     */
    public Item() {
    }

    @Builder
    public Item(Long itemNo, String itemName, int price, int stock) {
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.price = price;
        this.stock = stock;
    }

    public static Item of(Long itemNo, String itemName, int price, int stock) {
        return new Item(itemNo, itemName, price, stock);
    }
}
