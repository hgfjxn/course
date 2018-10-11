package win.hgfdodo.course.config;

import org.apache.thrift.TServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import win.hgfdodo.course.user.UserService;

@Configuration
public class Config {

    @Bean
    public UserService.Iface getUserService(TServiceClient serviceClient){
        return (UserService.Iface) serviceClient;
    }
}
