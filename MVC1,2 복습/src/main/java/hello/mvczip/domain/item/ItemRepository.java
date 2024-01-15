package hello.mvczip.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    private static Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public Item updateItem(long id, Item updateItem) {
        Item item = findById(id);
        item.setItemName(updateItem.getItemName());
        item.setPrice(updateItem.getPrice());
        item.setQuantity(updateItem.getQuantity());
        return item;
    }

    public void clear(){
        store.clear();
    }

}
