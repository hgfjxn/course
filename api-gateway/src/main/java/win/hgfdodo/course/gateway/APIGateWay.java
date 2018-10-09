package win.hgfdodo.course.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class APIGateWay {
    public static void main(String[] args) {
        SpringApplication.run(APIGateWay.class, args);
    }
}