package kr.co._29cm.homework.controller.cart;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import kr.co._29cm.homework.axiom.enums.InputType;
import kr.co._29cm.homework.axiom.number.NumberChecker;
import kr.co._29cm.homework.core.cart.Cart;
import kr.co._29cm.homework.core.cart.command.CartCreator;
import kr.co._29cm.homework.core.cart.command.CartDeleter;
import kr.co._29cm.homework.core.cart.query.CartSearcher;
import kr.co._29cm.homework.core.item.Item;
import kr.co._29cm.homework.core.item.SoldOutException;
import kr.co._29cm.homework.core.item.command.ItemUpdater;
import kr.co._29cm.homework.core.item.query.ItemSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

import static kr.co._29cm.homework.axiom.space.EmptyChecker.isEmptyCheck;
import static kr.co._29cm.homework.axiom.space.EmptyChecker.isEmptyOrder;
import static kr.co._29cm.homework.controller.cart.CartOrderResponseDto.displayCurrency;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final ItemSearcher itemSearcher;
    private final CartCreator cartCreator;
    private final CartSearcher cartSearcher;
    private final CartDeleter cartDeleter;
    private final ItemUpdater itemUpdater;
    private final ApplicationContext context;

    Scanner sc = new Scanner(System.in);

    /**
     * 상품 목록을 출력합니다.
     */
    public List<Item> itemList() {
        return itemSearcher.findAll();
    }

    public void progress() {
        String input = commandOrder();
        orderItemDisplay(input);
        Multimap<String, String> cartMap = ArrayListMultimap.create();

        boolean isProgress = true;
        while (isProgress) {
            String sId = UUID.randomUUID().toString();

            String itemNo = commandItemNo();
            String quantity = commandQuantity();

            if (!isEmptyCheck(itemNo) && !isEmptyCheck(quantity)) {
                cartMap.put(itemNo, quantity);
            }

            if (isEmptyOrder(itemNo, quantity)) {
                try {
                    createOrder(cartMap, sId);
                    if (cartMap.size() == 0) {
                        continue;
                    }
                    itemUpdater.update(sId);
                } catch (SoldOutException e) {
                    System.out.println("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
                    cartMap = cartInitialization(sId);
                    commandOrder();
                    continue;
                }
                printCartItemList(sId);
                printPaymentAmount(sId);
                cartMap = cartInitialization(sId);
                commandOrder();
            }
        }
    }

    public Multimap<String, String> cartInitialization(String sId) {
        Multimap<String, String> cartMap = ArrayListMultimap.create();
        cartDeleter.deleteBySessionId(sId);
        return cartMap;
    }

    public String commandItemNo() {
        System.out.print("상품번호 : ");
        String itemNo = sc.nextLine();
        if (!isNumber(itemNo) && !isEmptyCheck(itemNo)) {
            System.out.println("상품번호는 숫자 또는 스페이스만 입력 가능합니다.");
            System.out.print("상품번호 : ");
            return sc.nextLine();
        }
        return itemNo;
    }

    public String commandQuantity() {
        System.out.print("수량 : ");
        String quantity = sc.nextLine();
        if (!isNumber(quantity) && !isEmptyCheck(quantity)) {
            System.out.println("수량은 숫자 또는 스페이스만 입력 가능합니다.");
            System.out.print("수량 : ");
            return sc.nextLine();
        }
        return quantity;
    }

    public boolean isNumber(String param) {
        return NumberChecker.isNumber(param);
    }

    public String commandOrder() {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        String input = sc.nextLine();

        try {
            if (!InputType.isOrder(input) && !InputType.isQuit(input)) {
                System.out.println("주문은 o 종료는 q[quit]를 입력 해주세요.");
                System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
                input = sc.nextLine();
                commandQuit(input);
            } else if (InputType.isQuit(input)) {
                System.out.println("고객님의 주문 감사합니다.");
                System.exit(SpringApplication.exit(context, () -> 0));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("주문은 o 종료는 q[quit]를 입력 해주세요.");
        }
        return input;
    }

    public void commandQuit(String input) {
        try {
            if (InputType.isQuit(input)) {
                System.out.println("고객님의 주문 감사합니다.");
                System.exit(SpringApplication.exit(context, () -> 0));
            }
        } catch (RuntimeException e) {
            System.out.println("주문은 o 종료는 q[quit]를 입력 해주세요.");
            commandOrder();
        }
    }

    public void orderItemDisplay(String input) {
        if (InputType.isOrder(input)) {
            itemList().forEach(System.out::print);
        }
    }


    private Multimap<String, String> createOrder(Multimap<String, String> cartMap, String sId) {
        final Iterator<String> iterator = cartMap.keySet().iterator();
        while (iterator.hasNext()) {
            String itemNo = iterator.next();
            int quantity = cartMap.get(itemNo).stream()
                    .filter(Objects::nonNull)
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .reduce(0, Integer::sum);

            try {
               Item savedItem = itemSearcher.findByItemNo(Long.parseLong(itemNo));
                cartCreator.create(Cart.of(sId, savedItem.getItemNo(), savedItem.getItemName(), savedItem.getPrice(), quantity));
            } catch (IllegalArgumentException e) {
                System.out.println("존재하지 않는 상품번호는 제외 됩니다.");
                iterator.remove();
            }
        }
        return cartMap;
    }

    public void printPaymentAmount(String sId) {
        CartOrderResponseDto orderDto = getCartItemList(sId);
        System.out.printf("주문금액: %s원 %n", displayCurrency(orderDto.getOrderAmount()));
        System.out.printf("배송비: %s원 %n", displayCurrency(orderDto.getDeliveryCharge()));
        System.out.println("--------------------");
        System.out.printf("지불금액: %s원 %n", displayCurrency(orderDto.getPaymentAmount()));
        System.out.println("--------------------");
    }

    public void printCartItemList(String sId) {
        List<CartResponseDto> cartResponseDtoList = cartSearcher.findBySessionId(sId).stream()
                .map(CartResponseDto::new)
                .collect(Collectors.toList());
        System.out.println("--------------------");
        cartResponseDtoList.forEach(System.out::println);
        System.out.println("--------------------");
    }

    public CartOrderResponseDto getCartItemList(String sId) {
        return new CartOrderResponseDto(cartSearcher.totalCart(sId));
    }
}
