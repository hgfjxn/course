package win.hgfdodo.thrift.config;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ThriftClientBuilder<T extends TServiceClient> {
    private ThriftConfig thriftConfig;

    public ThriftClientBuilder<T> clientType(Class<? extends TServiceClient> type) {
        this.thriftConfig.setType(type);
        return this;
    }

    public ThriftClientBuilder<T> address(String address) {
        this.thriftConfig.setAddress(address);
        return this;
    }

    public ThriftClientBuilder<T> port(int port) {
        this.thriftConfig.setPort(port);
        return this;
    }

    public ThriftClientBuilder<T> thriftConfig(ThriftConfig thriftConfig){
        this.thriftConfig = thriftConfig;
        return this;
    }

    public T build() {
        TSocket socket = new TSocket(thriftConfig.getAddress(), thriftConfig.getPort());
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            Constructor<TServiceClient> constructor = thriftConfig.getType().getConstructor(TProtocol.class);
            TServiceClient client = constructor.newInstance(protocol);

            return (T) client;
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }
}
