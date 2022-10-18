package kr.co._29cm.homework.core.item;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue
    private  Long id;
    private int itemNo;
    private String itemName;
    private int price;
    private int stock;

    /**
     * jpa가 필요로 합니다.
     */
    public Item() {
    }

    public Item(int itemNo, String itemName, int price, int stock) {
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.price = price;
        this.stock = stock;
    }
}
