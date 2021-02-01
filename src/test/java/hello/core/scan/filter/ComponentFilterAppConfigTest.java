package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = applicationContext.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            // `type=FilterType.ANNOTATION`은 기본 값이라 사실 생략해도 잘 동작한다.
            includeFilters = @Filter(type= FilterType.ANNOTATION, classes = MyIncludeComponent.class)
            , excludeFilters = {
                    @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
                    // , @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
            }
    )
    static class ComponentFilterAppConfig {

    }
}
