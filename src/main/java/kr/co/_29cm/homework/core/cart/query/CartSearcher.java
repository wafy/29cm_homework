package kr.co._29cm.homework.core.cart.query;

import kr.co._29cm.homework.core.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartSearcher {

    private final CartSearcherRepository searcherRepository;

    public List<Cart> findBySessionId(String sessionId) {
        return searcherRepository.findBySessionId(sessionId);
    }
}
