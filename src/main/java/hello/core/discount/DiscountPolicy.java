package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     * @return 얼마가 할인이 되어야 하는지?
     */
    int discount(Member member, int price);
}
