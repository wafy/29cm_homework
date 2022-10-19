package kr.co._29cm.homework.core.order.command;

import kr.co._29cm.homework.core.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
