package kr.co._29cm.homework.core.cart.command;

import kr.co._29cm.homework.core.cart.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
