package win.hgfdodo.course.user.service;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.context.annotation.Bean;
import win.hgfdodo.course.user.UserService;

public class UserServiceImplTest {

    @Bean
    public void init() throws TTransportException {
        TSocket socket = new TSocket("localhost", 8080);
        TTransport transport = new TFramedTransport(socket);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        UserService.Client client = new UserService.Client(protocol);
    }

    public void getUserById() {
    }
}