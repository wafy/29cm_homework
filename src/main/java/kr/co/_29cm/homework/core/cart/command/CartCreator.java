package kr.co._29cm.homework.core.cart.command;

import kr.co._29cm.homework.core.cart.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartCreator {
    private final CartRepository cartRepository;

    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }
}
