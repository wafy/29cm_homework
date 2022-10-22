package kr.co._29cm.homework;

import kr.co._29cm.homework.controller.cart.CartController;
import kr.co._29cm.homework.controller.item.ItemCreateController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
@RequiredArgsConstructor
public class SimpleApplicationRunner implements ApplicationRunner {
    private final CartController cartController;
    private final ItemCreateController itemCreateController;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        itemCreateController.create();
        cartController.progress();
    }

}
