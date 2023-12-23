package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class PixDiscountPolicy implements DiscountPolicy{

    private int discountPixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return discountPixAmount;
        } else {
            return 0;
        }
    }
}
