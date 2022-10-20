package kr.co._29cm.homework.core.item.query;

import kr.co._29cm.homework.core.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface ItemSearcherRepository extends JpaRepository<Item, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Item> findById(Long itemNo);

    @Query("select i from Item i where i.itemNo in :itemNos")
    List<Item> findByIdIn(List<Long> itemNos);
}
