package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시에 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> applicationContext.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시에 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy
                = applicationContext.getBean("rateDiscountPolicy", DiscountPolicy.class);

        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회 (안좋은 방법)")
    void findBeanBySubType() {
        RateDiscountPolicy rateDiscountPolicy = applicationContext.getBean(RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    /*
    나중에 테스트의 성공 여부는 시스템이 결정해야 하므로
    출력물로 결정하면 안된다.

    그러나 디버깅을 잠깐 해보고 싶을 때는 이렇게 출력을 활용해볼 수 있다.
     */
    void findBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = applicationContext.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }


    @Test
    @DisplayName("Object 타입으로 모두 조회")
    void findBeanByObjectType() {
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    /*
    사실 실제로 개발을 할 때는
    이렇게 `applicationContext`를 이용해 `Bean`을 조회할 일은 거의 없다.

    이걸 설명하는 이유는 기본 기능이기도 하고,
    가끔 순수한 자바 어플리케이션에서 스프링 컨테이너를 만들 때도 있어서이다.

    또 부모 타입으로 조회했을 때, 자식이 어디까지 조회가 되는지 알아야
    나중에 `@Autowired` 등의 어노테이션을 제대로 쓸 수 있기 때문이다.
     */

    @Configuration
    static class TestConfig {
        /*
        리턴 타입을 `DiscountPolicy`로 해주는 이유는,
        리턴 값의 `구현체`를 보는 것이 아니라, `역할`을 보려고 하는 것이다.
         */
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
