package kr.co._29cm.homework.assemble;

import kr.co._29cm.homework.core.cart.Cart;
import kr.co._29cm.homework.core.cart.command.CartCreator;
import kr.co._29cm.homework.core.cart.query.CartSearcher;
import kr.co._29cm.homework.core.item.Item;
import kr.co._29cm.homework.core.item.command.ItemCreator;
import kr.co._29cm.homework.core.item.query.ItemSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Controller
@RequiredArgsConstructor
public class AssembleController {

    private final ItemCreator itemCreator;
    private final ItemSearcher itemSearcher;
    private final CartCreator cartCreator;
    private final CartSearcher cartSearcher;

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
                addCart(itemNoList, quantityList);
                showCart();
            }
        }
    }

    private void showCart() {
        List<CartDto> cartDtoList = cartSearcher.findBySessionId(sId).stream()
                .map(CartDto::new)
                .collect(Collectors.toList());
        System.out.println("--------------------");
        cartDtoList.stream()
                .forEach(System.out::println);
        System.out.println("--------------------");
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
        for (int i=0; i < itemList.size(); i++) {
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
