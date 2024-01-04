package hello.itemservicereview.domain;

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
        //given
        Item itemA = new Item("itemA", 10000, 1);

        //when
        Item savedItem = itemRepository.save(itemA);

        //then
        Item findItem = itemRepository.findById(itemA.getId());
        assertThat(savedItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 10000, 1);
        Item itemB = new Item("itemB", 20000, 2);
        //when
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        //then
        List<Item> result = itemRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);

    }

    @Test
    void update() {
        //given
        Item itemA = new Item("itemA", 10000, 1);
        itemRepository.save(itemA);
        //when
        Item updateParam = new Item("itemB", 20000, 2);
        itemRepository.update(itemA.getId(), updateParam);
        //then
        assertThat(itemA.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(itemA.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(itemA.getQuantity()).isEqualTo(updateParam.getQuantity());


    }


}