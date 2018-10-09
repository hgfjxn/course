package win.hgfdodo.thrift.config;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Configuration
@EnableConfigurationProperties(ThriftConfig.class)
public class ThriftClientAutoConfiguration {

    private final static Logger log = LoggerFactory.getLogger(ThriftClientAutoConfiguration.class);

    private final ThriftConfig thriftConfig;

    public ThriftClientAutoConfiguration(ThriftConfig thriftConfig) {
        this.thriftConfig = thriftConfig;
    }

    @Bean
    @ConditionalOnMissingBean(TServiceClient.class)
    @ConditionalOnClass(TServiceClient.class)
// TODO  配置类会创建Bean吗？为什么thriftconfig已经有了实例却不能满足 @ConditionalOnBean(ThriftConfig.class)
    public TServiceClient getThriftClient() {

        log.info("generate thrift client with framed transport and binary protocol!");
        return new ThriftClientBuilder().thriftConfig(thriftConfig).build();
    }
}
