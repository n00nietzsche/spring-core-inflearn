package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("`memberService`와 `orderService`의 `memberRepository`를 확인하여 싱글톤이 잘 적용되는지 테스트")
    void configurationTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        /*
        원래는 구체 타입으로 꺼내면 안좋다. 이번엔 테스트 용임.
         */
        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = applicationContext.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository of ApplicationContext = " + memberRepository);

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        /*
        `@Configuration`인 클래스인 `AppConfig.class`를 넘기면
        `AppConfig` 마저도 `Bean`이 된다.
         */
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = applicationContext.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
    }
}
