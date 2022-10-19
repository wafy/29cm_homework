package kr.co._29cm.homework.core.item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
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

    /**
     * 현재 재고 수량이 0이거나 주문수량의 차이가 0보다 작으면 예외 발생
     * @param  quantity 주문 수량
     * @return
     * @throws SoldOutException
     */
    public int stockDeduction(int quantity) throws SoldOutException {
        if ((this.stock - quantity) <= 0) {
            throw new SoldOutException("재고가 부족합니다.");
        }
        return this.stock - quantity;
    }
}
