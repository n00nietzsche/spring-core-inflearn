package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    /*
    AnnotationConfigApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(AppConfig.class);
     */

    GenericXmlApplicationContext applicationContext
            = new GenericXmlApplicationContext("appConfig.xml");


    /*
    `beanDefinition`:
    `beanClass`:
    `scope`:
    `abstract`:
    `lazyInit`:
    `autowireMode`: `@Autowired`의 후보가 될지 모드
    `dependencyCheck`:
    `primary`:
    `factoryBeanName`: 어떤 클래스에 찾아가서 `Bean`을 생성할 수 있는지
    `factoryMethodName`: 생성 클래스에서 `Bean`을 생성하는 메소드는?
    `initMethodName`: 초기화 시 실행될 메소드 이름
    `destroyMethodName`: 종료 시 실행될 메소드 이름
     `defined in ...`: `...`에 정의되어 있다.
     */

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            /*
            해당 메소드는 `AnnotationConfigApplicationContext` 클래스에만 있으며,
            `ApplicationContext` 인터페이스에는 없다.
             */
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " beanDefinition = " + beanDefinition);
            }
        }
    }
}
