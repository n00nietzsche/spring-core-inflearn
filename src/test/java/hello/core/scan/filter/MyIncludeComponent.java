package hello.core.scan.filter;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/*
`@target`이 `TYPE`이라 하면 클레스 레벨에 붙는 것이다.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
