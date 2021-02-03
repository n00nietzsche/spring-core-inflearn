package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// javax 에서 지원하는 것은 자바 진영에서 공식적으로 지원하는 어노테이션이다.
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }


    // 스프링 의존관계 주입이 끝나고 호출
    @PostConstruct
    public void initialize() throws Exception {
        System.out.println("NetworkClient.initialize");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
