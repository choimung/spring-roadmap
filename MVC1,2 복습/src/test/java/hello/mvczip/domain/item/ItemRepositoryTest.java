package hello.mvczip.domain.item;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clear();
    }

    @Test
    void save() {
        Item itemA = new Item("itemA", 10000, 10);

        Item saveditem = itemRepository.save(itemA);

        Item findItem = itemRepository.findById(itemA.getId());

        assertThat(saveditem).isEqualTo(findItem);

    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        List<Item> result = itemRepository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}