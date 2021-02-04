package hello.core.web;

import hello.core.common.WebLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final WebLogger webLogger;

    public void logic(String id) {
        webLogger.log("service id = " + id);
    }
}
