package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        /*
        스프링에서는 `ApplicationContext`가 모든 것을 다 관리해준다.
        아래와 같이 `new AnnotationConfigApplicationContext(AppConfig.class)`를 이용하여 인스턴스화 하면
        `AppConfig` 클래스에 존재하는 어노테이션을 통해서 스프링의 `설정 영역`을 설정하게 된다.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        /*
        `@Bean`의 기본 이름은 메소드의 이름으로 등록되기 때문에
        `"memberService"`로 해당 `@Bean`을 꺼내올 수 있다.
         */
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println(member.getName());
        System.out.println(findMember.getName());
    }
    /*
    위와 같은 방식으로 앱을 실행하게 되면, 기존처럼 내가 출력한 것들(멤버의 이름)만 로그에 뜨는 것이 아니라,
    스프링이 초기화 되는 과정도 전부 로그에 남게 된다.
     */
}
