package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.core.item.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    Item save(Item item);
}
