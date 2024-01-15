package hello.mvczip;

import hello.mvczip.domain.item.Item;
import hello.mvczip.domain.item.ItemRepository;
import hello.mvczip.domain.member.Member;
import hello.mvczip.domain.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Item itemA = new Item("itemA", 1000, 10);
        Item itemB = new Item("itemB", 2000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        Member member = new Member("test", "test!", "테스터");
        memberRepository.save(member);
    }


}
