package com.example.demo.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

    MemberRepository memberRepository = new MemberRepository();

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        memberRepository.save(item);

        Item findItem = memberRepository.findById(item.getId());

        //then
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 10000, 10);

        memberRepository.save(itemA);
        memberRepository.save(itemB);

        List<Item> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);
    }


    @Test
    void updateParam() {
        Item itemA = new Item("itemA", 10000, 10);
        memberRepository.save(itemA);


        Item updateParam = new Item("itemB", 20000, 20);
        memberRepository.update(itemA.getId(), updateParam);

        assertThat(updateParam.getItemName()).isEqualTo(itemA.getItemName());
        assertThat(updateParam.getPrice()).isEqualTo(itemA.getPrice());
        assertThat(updateParam.getQuantity()).isEqualTo(itemA.getQuantity());
    }

}