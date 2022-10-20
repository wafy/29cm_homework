package kr.co._29cm.homework;

import kr.co._29cm.homework.assemble.AssembleController;
import kr.co._29cm.homework.consts.InputType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final AssembleController assembleController;
    private final ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {

        assembleController.itemCreate();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("입력(o[order]: 주문, q[quit]: 종료) :");
            String input = sc.nextLine();
            if (Objects.equals(input, InputType.ORDER.getValue())) {
                assembleController.showItems();
                assembleController.saveCart(sc);
            } else if (Objects.equals(input, InputType.QUIT.getValue())) {
                System.out.println("고객님의 주문 감사합니다.");
                System.exit(SpringApplication.exit(context));
            }
        }
    }
}
