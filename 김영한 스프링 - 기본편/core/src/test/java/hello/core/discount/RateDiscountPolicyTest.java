package hello.core.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        //given
        Member memberVip = new Member(1L, "memberVip", Grade.VIP);

        //when
        int discount = rateDiscountPolicy.discount(memberVip, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        //given
        Member memberVip = new Member(1L, "memberVip", Grade.BASIC);

        //when
        int discount = rateDiscountPolicy.discount(memberVip, 10000);

        //then
        assertThat(discount).isEqualTo(0);
    }
}