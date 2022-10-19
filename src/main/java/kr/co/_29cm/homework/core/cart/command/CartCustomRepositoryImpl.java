package kr.co._29cm.homework.core.cart.command;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co._29cm.homework.core.cart.Cart;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static kr.co._29cm.homework.core.cart.QCart.cart;

@Repository
@Transactional
public class CartCustomRepositoryImpl implements CartRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public CartCustomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public Cart save(Cart cart) {
        em.persist(cart);
        return cart;
    }

    @Override
    public void deleteAll() {
        query.delete(cart);
    }

    @Override
    public long deleteBySessionId(String sessionId) {
        return query.delete(cart)
                .where(cart.sessionId.eq(sessionId))
                .execute();
    }
}
