package win.hgfdodo.course.user;

import org.apache.thrift.TServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import win.hgfdodo.thrift.config.ThriftConfig;

@SpringBootApplication
public class UserEdgeServiceApplication implements CommandLineRunner {
    @Autowired
    ThriftConfig thriftConfig;

    @Autowired
    TServiceClient userClient;

    public static void main(String[] args) {
        SpringApplication.run(UserEdgeServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(thriftConfig.toString());
        System.out.println(userClient);
    }
}
