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

    // @Bean memberService
    //   -> new MemoryMemberRepository()
    // @Bean orderService
    //   -> new OrderServiceImpl(memberRepository(), discountPolicy())
    //     -> new MemoryMemberRepository()
    //     -> new RateDiscountPolicy()

    /*
    설정 영역에 등록할 객체들에는 `@Bean` 어노테이션을 붙여준다.
     */

    /*
    예상 출력
call AppConfig.memberRepository
call AppConfig.memberService
call AppConfig.memberRepository
call AppConfig.orderService
call AppConfig.memberRepository

    `memberRepository`가 3번 호출될 것이라 예상함.

    근데 실제 결과는

    call AppConfig.memberRepository
    call AppConfig.memberService
    call AppConfig.orderService

    위와 같이 나옴
     */
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository()
                , discountPolicy()
        );
    }
}

