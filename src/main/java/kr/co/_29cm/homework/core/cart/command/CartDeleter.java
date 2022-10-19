package kr.co._29cm.homework.core.cart.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartDeleter {
    private final CartRepository cartRepository;

    public long deleteBySessionId(String sessionId) {
       return cartRepository.deleteBySessionId(sessionId);
    }

}
