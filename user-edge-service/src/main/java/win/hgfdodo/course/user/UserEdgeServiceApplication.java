package win.hgfdodo.course.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import win.hgfdodo.course.message.MessageService;
import win.hgfdodo.course.user.config.MultiThriftClientConfig;

@SpringBootApplication
@EnableConfigurationProperties(MultiThriftClientConfig.class)
public class UserEdgeServiceApplication implements CommandLineRunner {

    @Autowired
    UserService.Client userClient;

    @Autowired
    MessageService.Client messageClient;

    @Autowired
    MultiThriftClientConfig multiThriftClientConfig;

    public static void main(String[] args) {
        SpringApplication.run(UserEdgeServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(userClient);
        System.out.println(messageClient);
        System.out.println(multiThriftClientConfig);
    }
}
