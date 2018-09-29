package win.hgfdodo.thrift.config;

import org.apache.thrift.TServiceClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "thrift")
public class ThriftConfig {

    private Class<? extends TServiceClient> type;

    private String address;

    private int port;

    public ThriftConfig() {
    }

    public ThriftConfig(Class type, String address, int port) {
        this.type = type;
        this.address = address;
        this.port = port;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ThriftConfig{" +
                "type=" + type +
                ", address='" + address + '\'' +
                ", port=" + port +
                '}';
    }
}
