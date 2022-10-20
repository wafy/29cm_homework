package kr.co._29cm.homework.core.item.command;

import kr.co._29cm.homework.core.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item save(Item item);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Item> findById(Long itemNo);
}
