package kr.co._29cm.homework.assemble;

import kr.co._29cm.homework.consts.InputType;
import kr.co._29cm.homework.core.cart.Cart;
import kr.co._29cm.homework.core.cart.command.CartCreator;
import kr.co._29cm.homework.core.cart.command.CartDeleter;
import kr.co._29cm.homework.core.cart.query.CartSearcher;
import kr.co._29cm.homework.core.item.Item;
import kr.co._29cm.homework.core.item.SoldOutException;
import kr.co._29cm.homework.core.item.command.ItemCreator;
import kr.co._29cm.homework.core.item.command.ItemUpdateStock;
import kr.co._29cm.homework.core.item.command.ItemUpdater;
import kr.co._29cm.homework.core.item.query.ItemSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AssembleController {

    private final ConfigurableApplicationContext context;

    private final ItemCreator itemCreator;
    private final ItemSearcher itemSearcher;
    private final CartCreator cartCreator;
    private final CartSearcher cartSearcher;
    private final CartDeleter cartDeleter;
    private final ItemUpdater itemUpdater;

    private String sId = UUID.randomUUID().toString();

    public void showItems() {
        List<Item> itemList = itemSearcher.findAll();
        itemList.forEach(System.out::print);
    }

    public void itemCreate() {
        itemCreator.create();
    }

    public void saveCart(Scanner sc) {
        List<String> itemNoList = new ArrayList<>();
        List<String> quantityList = new ArrayList<>();

        while (true) {
            System.out.print("상품번호 : ");
            String itemNo = sc.nextLine();

            System.out.print("수량 : ");
            String quantity = sc.nextLine();
            if (!isEmptyCheck(quantity)) {
                itemNoList.add(itemNo);
                quantityList.add(quantity);
            }

            if (isEmptyCheck(itemNo) && isEmptyCheck(quantity)) {
                try {
                    itemUpdater.update(sId);
                } catch (SoldOutException e) {
                    System.out.println("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
                    System.out.println("입력(o[order]: 주문, q[quit]: 종료) :");
                    String input = sc.nextLine();
                    if (Objects.equals(input, InputType.ORDER.getValue())) {
                        showItems();
                        addCart(itemNoList, quantityList);
                    } else if (Objects.equals(input, InputType.QUIT.getValue())) {
                        System.out.println("고객님의 주문 감사합니다.");
                        cartDeleter.deleteBySessionId(sId);
                        System.exit(SpringApplication.exit(context));
                    }
                }
                addCart(itemNoList, quantityList);
                showCart();
                showOrderCart();
            }
            System.out.println("입력(o[order]: 주문, q[quit]: 종료) :");
            String input = sc.nextLine();
            if (Objects.equals(input, InputType.ORDER.getValue())) {
                showItems();
                addCart(itemNoList, quantityList);
            } else if (Objects.equals(input, InputType.QUIT.getValue())) {
                System.out.println("고객님의 주문 감사합니다.");
                cartDeleter.deleteBySessionId(sId);
                System.exit(SpringApplication.exit(context));
            }
        }
    }

    private void showOrderCart() {
        CartOrderResponseDto orderDto = getOrder();
        System.out.println(String.format("주문금액: %d원", orderDto.getOrderAmount()));
        System.out.println(String.format("배송비: %d원", orderDto.getDeliveryCharge()));
        System.out.println("--------------------");
        System.out.println(String.format("지불금액: %d원", orderDto.getPaymentAmount()));
        System.out.println("--------------------");
    }
    private void showCart() {
        List<CartResponseDto> cartResponseDtoList = cartSearcher.findBySessionId(sId).stream()
                .map(CartResponseDto::new)
                .collect(Collectors.toList());
        System.out.println("--------------------");
        cartResponseDtoList.forEach(System.out::println);
        System.out.println("--------------------");
    }

    private CartOrderResponseDto getOrder() {
        return new CartOrderResponseDto(cartSearcher.totalCart(sId));
    }

    public void addCart(List<String> itemNoList, List<String> quantityList) {
        List<Long> itemLongList = itemNoList.stream()
                .map(String::trim)
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> quantityIntegerList = quantityList.stream()
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        List<Item> itemList = itemSearcher.findByItemNo(itemLongList);
        for (int i = 0; i < itemList.size(); i++) {
            cartCreator.add(Cart.of(sId,
                    itemList.get(i).getItemNo(),
                    itemList.get(i).getItemName(),
                    itemList.get(i).getPrice(),
                    quantityIntegerList.get(i)));
        }
    }


    public static boolean isEmptyCheck(String input) {
        return input == null || input.trim().isEmpty();
    }
}
