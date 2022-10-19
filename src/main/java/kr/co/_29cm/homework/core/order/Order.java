package kr.co._29cm.homework.core.order;

import kr.co._29cm.homework.core.item.Item;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemNo")
    private Item item;

    private int quantity;

    private LocalDateTime created;

}
