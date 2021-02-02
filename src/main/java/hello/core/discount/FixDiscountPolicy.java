package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        // Enum 을 비교할 때는 == 으로 비교하는 것이 맞다.
        // 문자열이나 객체의 내용을 비교할 때는 `Equals`
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        return 0;
    }
}
