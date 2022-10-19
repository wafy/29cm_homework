package kr.co._29cm.homework.core.cart.query;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co._29cm.homework.core.cart.Cart;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static kr.co._29cm.homework.core.cart.QCart.cart;

@Repository
public class CartSearcherCustomRepositoryImpl implements CartSearcherRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public CartSearcherCustomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Cart> findBySessionId(String sessionId) {
        return query
                .select(cart)
                .from(cart)
                .where(equalSessionId(sessionId))
                .fetch();
    }

    private Predicate equalSessionId(String sessionId) {
       if (StringUtils.hasText(sessionId)) {
           return cart.sessionId.eq(sessionId);
       }
       return null;
    }
}
