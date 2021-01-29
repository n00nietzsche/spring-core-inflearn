package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 자동으로 스프링 빈을 끌어올리기 위함
@ComponentScan (
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION, classes = Configuration.class
        )

        // 위는 `@Configuration` 애노테이션이 달린 다른 클래스들을 자동으로 빈에 등록하지 않기 위함이다.
        // 자동으로 등록되면 충돌이 날 수 있기 때문에, `excludeFilters`를 이용해서 빼주었다.
        , basePackageClasses = AutoAppConfig.class
        , basePackages = "hello.core"
        // , basePackages = {"hello.core", "hello.service"}
)
public class AutoAppConfig {

}
