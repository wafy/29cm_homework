package kr.co._29cm.homework.controller.cart;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import kr.co._29cm.homework.consts.InputType;
import kr.co._29cm.homework.core.cart.Cart;
import kr.co._29cm.homework.core.cart.command.CartCreator;
import kr.co._29cm.homework.core.cart.command.CartDeleter;
import kr.co._29cm.homework.core.cart.query.CartSearcher;
import kr.co._29cm.homework.core.item.Item;
import kr.co._29cm.homework.core.item.SoldOutException;
import kr.co._29cm.homework.core.item.command.ItemUpdater;
import kr.co._29cm.homework.core.item.query.ItemSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

import static kr.co._29cm.homework.controller.cart.CartOrderResponseDto.displayCurrency;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final ItemSearcher itemSearcher;
    private final CartCreator cartCreator;
    private final CartSearcher cartSearcher;
    private final CartDeleter cartDeleter;
    private final ItemUpdater itemUpdater;

    private String sId = UUID.randomUUID().toString();
    Multimap<String, String> cartMap = ArrayListMultimap.create();
    Scanner sc = new Scanner(System.in);

    /**
     * 상품 목록을 출력합니다.
     */
    public void itemList() {
        List<Item> itemList = itemSearcher.findAll();
        itemList.forEach(System.out::print);
    }

    public void progress() {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        String input = sc.nextLine();
        
        orderItemDisplay(input);

        boolean isProgress = true;
        while (isProgress) {
            System.out.print("상품번호 : ");
            String itemNo = sc.nextLine();

            System.out.print("수량 : ");
            String quantity = sc.nextLine();
            if (!isEmptyCheck(itemNo) && !isEmptyCheck(quantity)) {
                cartMap.put(itemNo, quantity);
            }

            if (isEmptyCheck(itemNo) && isEmptyCheck(quantity)) {
                try {
                    createOrder();
                    itemUpdater.update(sId);
                } catch (SoldOutException e) {
                    System.out.println("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
                    isProgress = isContinueOrder(sc);
                    if (!isProgress) {
                        cartDeleter.deleteBySessionId(sId);
                        break;
                    }
                }
                showTempCart();
                showOrderCart();
                if (!isContinueOrder(sc)) {
                   cartDeleter.deleteBySessionId(sId);
                    break;
                }
            }
        }
    }

    private boolean isContinueOrder(Scanner sc) {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        String input = sc.nextLine();
        return !Objects.equals(input, InputType.QUIT.getValue());
    }
    

    private void orderItemDisplay(String input) {
        if (Objects.equals(input, InputType.ORDER.getValue())) {
            itemList();
        }
    }

    private void createOrder() {
        final Iterator<String> iterator = cartMap.keySet().iterator();
        while (iterator.hasNext()) {
            String itemNo = iterator.next();
            int quantity = cartMap.get(itemNo).stream()
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .reduce(0, Integer::sum);

            Item savedItem = itemSearcher.findByItemNo(Long.parseLong(itemNo));

            cartCreator.create(Cart.of(sId, savedItem.getItemNo(), savedItem.getItemName(), savedItem.getPrice(), quantity));
        }
    }

    private void showOrderCart() {
        CartOrderResponseDto orderDto = getCartItemList();
        System.out.printf("주문금액: %s원 %n", displayCurrency(orderDto.getOrderAmount()));
        System.out.printf("배송비: %s원 %n", displayCurrency(orderDto.getDeliveryCharge()));
        System.out.println("--------------------");
        System.out.printf("지불금액: %s원 %n", displayCurrency(orderDto.getPaymentAmount()));
        System.out.println("--------------------");
    }

    private void showTempCart() {
        List<CartResponseDto> cartResponseDtoList = cartSearcher.findBySessionId(sId).stream()
                .map(CartResponseDto::new)
                .collect(Collectors.toList());
        System.out.println("--------------------");
        cartResponseDtoList.forEach(System.out::println);
        System.out.println("--------------------");
    }

    private CartOrderResponseDto getCartItemList() {
        return new CartOrderResponseDto(cartSearcher.totalCart(sId));
    }

    public static boolean isEmptyCheck(String input) {
        return input == null || input.trim().isEmpty();
    }
}
