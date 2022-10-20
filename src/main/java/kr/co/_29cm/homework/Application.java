package kr.co._29cm.homework;

import kr.co._29cm.homework.assemble.AssembleController;
import kr.co._29cm.homework.consts.InputType;
import kr.co._29cm.homework.core.cart.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final AssembleController assembleController;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {

        assembleController.itemCreate();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("입력(O[order]: 주문, q[quit]: 종료) :");
            if (Objects.equals(sc.nextLine(), InputType.ORDER.getValue())) {
                assembleController.showItems();
                assembleController.saveCart(sc);
            }
        }
    }
}
