package hello.mvczip;

import hello.mvczip.domain.item.Item;
import hello.mvczip.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        Item itemA = new Item("itemA", 1000, 10);
        Item itemB = new Item("itemB", 2000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
    }


}
