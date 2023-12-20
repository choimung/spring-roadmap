package hello.core.discount;

import hello.core.Member.Grade;
import hello.core.Member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPersecnt = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPersecnt / 100;
        }else{
            return 0;
        }
    }
}
