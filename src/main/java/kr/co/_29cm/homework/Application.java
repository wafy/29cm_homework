package kr.co._29cm.homework;

import kr.co._29cm.homework.controller.cart.CartController;
import kr.co._29cm.homework.controller.item.ItemCreateController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final CartController cartController;
    private final ItemCreateController itemCreateController;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        itemCreateController.create();
        cartController.progress();
    }
}
