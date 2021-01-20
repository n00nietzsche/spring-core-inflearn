package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
`@Configuration` 어노테이션을 이용함으로써,
스프링 컨테이너에게 이 클래스가 `사용 영역`과 `설정 영역` 중 `설정 영역`임을 알릴 수 있다.
 */
@Configuration
public class AppConfig {
    /* 나중에 구현체가 바뀔 때는 위의 구현체를 반환하는 부분만 바꿔주면 된다. */

    /*
    설정 영역에 등록할 객체들에는 `@Bean` 어노테이션을 붙여준다.
     */
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository()
                , discountPolicy()
        );
    }
}

