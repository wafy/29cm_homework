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

    @ManyToOne
    @JoinColumn(name = "itemNo")
    private Item item;

    private int quantity;

    public Cart() {
    }

    private Cart(Item item, String sessionId, int quantity) {
        this.item = item;
        this.sessionId = sessionId;
        this.quantity = quantity;
    }

    public static Cart of(Item item, String sessionId, int quantity) {
        return new Cart(item, sessionId, quantity);
    }
}
