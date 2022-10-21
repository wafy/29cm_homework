package kr.co._29cm.homework.controller.item;

import kr.co._29cm.homework.core.item.command.ItemCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ItemCreateController {
    private final ItemCreator itemCreator;

    public void create() {
        itemCreator.create();;
    }
}
