package hello.core.member;

import hello.core.AppConfig;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {


        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        memberService = applicationContext.getBean("memberService", MemberService.class);
        /*
        getBean 메소드에서 ""를 입력 후에 커서를 가운데로 옮기고 `Ctrl + Enter`를 입력하면,
        스프링 컨테이너에 등록된 `@Bean`들을 볼 수 있다.
         */
    }

    @Test
    @DisplayName("회원가입")
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
