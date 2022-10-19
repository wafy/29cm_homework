package kr.co._29cm.homework.core.cart;

import kr.co._29cm.homework.core.item.Item;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    private Long cartId;
    private String sessionId;
    private Long itemNo;
    private String itemName;
    private int price;
    private int quantity;

    public Cart() {
    }

    private Cart(String sessionId, Long itemNo, String itemName, int price, int quantity) {
        this.sessionId = sessionId;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public static Cart of(String sessionId, Long itemNo, String itemName, int price, int quantity) {
        return new Cart(sessionId, itemNo, itemName, price, quantity);
    }
}
