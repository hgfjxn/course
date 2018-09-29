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
    public TServiceClient getThriftClient(){

        log.info("generate thrift client with framed transport and binary protocol!");

        TSocket socket = new TSocket(thriftConfig.getAddress(), thriftConfig.getPort());
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            Constructor<TServiceClient> constructor = thriftConfig.getType().getConstructor(TProtocol.class);
            TServiceClient client = constructor.newInstance(protocol);

            return client;
        } catch (TTransportException e) {
            log.error("transport open error: {}", e);
        } catch (NoSuchMethodException e) {
            log.error("java reflect construction ,no method error: {}", e);
        } catch (IllegalAccessException e) {
            log.error("java reflect construction ,illegal access error: {}", e);
        } catch (InstantiationException e) {
            log.error("generate new instant error: {}", e);
        } catch (InvocationTargetException e) {
            log.error("invocation construction method error: {}", e);
        }

        return null;
    }
}
