package win.hgfdodo.course.user.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import win.hgfdodo.course.message.MessageService;
import win.hgfdodo.course.user.UserService;
import win.hgfdodo.thrift.config.ThriftClientBuilder;
import win.hgfdodo.thrift.config.ThriftConfig;

@Configuration
@ConfigurationProperties
public class MultiThriftClientConfig {
    private static final Logger log = LoggerFactory.getLogger(MultiThriftClientConfig.class);

    private ThriftConfig user;
    private ThriftConfig message;

//    public MultiThriftClientConfig(ThriftConfig user, ThriftConfig message) {
//        this.user = user;
//        this.message = message;
//        System.out.println(System.nanoTime()+" construct :" + user + ", " + message);
//    }

    public ThriftConfig getUser() {
        return user;
    }

    public void setUser(ThriftConfig user) {
//        System.out.println(System.nanoTime()+" set user:" + user);
        this.user = user;
    }

    public ThriftConfig getMessage() {
        return message;
    }

    public void setMessage(ThriftConfig message) {
//        System.out.println(System.nanoTime()+" set message:" + message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "MultiThriftClientConfig{" +
                "user=" + user +
                ", message=" + message +
                '}';
    }

    @Bean
    public UserService.Client getUserService() {
        log.info("user thrift service  config : {}" , user);
        return new ThriftClientBuilder<UserService.Client>().thriftConfig(user).build();
    }

    @Bean
    public MessageService.Client getMessageService() {
        log.info("message thrift service config : {}" , user);
        return new ThriftClientBuilder<MessageService.Client>().thriftConfig(message).build();
    }
}
