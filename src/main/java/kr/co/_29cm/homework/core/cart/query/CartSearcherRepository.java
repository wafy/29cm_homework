package kr.co._29cm.homework.core.cart.query;

import kr.co._29cm.homework.core.cart.Cart;

import java.util.List;

public interface CartSearcherRepository {

    List<Cart> findBySessionId(String sessionId);

}
