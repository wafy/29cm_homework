package kr.co._29cm.homework.core.item.query;

import kr.co._29cm.homework.core.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSearcherRepository extends JpaRepository<Item, Long> {
}
