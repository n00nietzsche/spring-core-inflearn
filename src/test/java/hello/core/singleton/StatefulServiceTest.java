package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void statefulServiceSingleton() {
        StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

        // Thread A: A 사용자가 10000원을 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // Thread B: B 사용자가 20000원을 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // Thread A: A 사용자 가 주문 금액을 조회
        // int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

        /*
        사용자 A가 주문한 내역이 사라지고,
        사용자 B가 주문한 것은 메모리에 남아있다.
         */
        Assertions.assertThat(userAPrice).isEqualTo(10000);
        Assertions.assertThat(userBPrice).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
