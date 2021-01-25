package hello.core.singleton;

public class SingletonService {

    /*
    자기 자신을 내부에 `private static final`로 딱 하나만 갖고 있음
     */
    private static final SingletonService instance = new SingletonService();

    /*
    내부적으로 프로그램이 실행될 때 생성된 `static`한 `instance`를 반환
     */
    public static SingletonService getInstance() {
        return instance;
    }

    /*
    `private` 제어자를 이용해서 자신의 클래스 이외의 영역에서
    생성자를 사용하여 `instance`를 만드는 것을 막아버림

    `new` 키워드를 이용해 생성할 수 없고,
    `getInstance`로 올려져있는 싱글톤 객체를 가져와서 사용할 수만 있음
     */
    private SingletonService () {

    }

    public void logic () {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
