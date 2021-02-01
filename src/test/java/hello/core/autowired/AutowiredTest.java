package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autoWiredOption() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        /*
        `Member` 클래스는 스프링이 관리하는 `@Bean`이 아니다.
         */
        @Autowired(required = false)
        public void setNoBean1(Member member1) {
            /*
            수정자 메소드 자체가 호출 안 됨
             */
            System.out.println("member1 = " + member1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member2) {
            /*
            메소드 호출은 되나, `null`이 들어감
             */
            System.out.println("member2 = " + member2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member3) {
            /*
            호출은 되나, `Optional.empty`가 들어감
             */
            System.out.println("member3 = " + member3);
        }
    }
}
