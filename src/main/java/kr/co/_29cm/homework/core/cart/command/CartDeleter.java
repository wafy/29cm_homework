package kr.co._29cm.homework.core.cart.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartDeleter {
    private final CartRepository cartRepository;

    @Transactional
    public long deleteBySessionId(String sessionId) {
       return cartRepository.deleteBySessionId(sessionId);
    }

}
