package kr.co._29cm.homework.core.cart.command;

import kr.co._29cm.homework.core.cart.Cart;

public interface CartRepository {

    Cart save(Cart cart);
    void deleteAll();
    long deleteBySessionId(String sessionId);
}
