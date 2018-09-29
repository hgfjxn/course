package win.hgfdodo.course.user;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(UserServiceApplication.class);

    @Value("${server.port}")
    private int port;

    @Autowired
    UserService.Iface userService;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.debug("user service port : {}", port);

        TProcessor processor = new UserService.Processor(userService);

        TNonblockingServerSocket socket = new TNonblockingServerSocket(port);
        TNonblockingServer.Args serverArgs = new TNonblockingServer.Args(socket);

        serverArgs.processor(processor);
        serverArgs.transportFactory(new TFramedTransport.Factory());
        serverArgs.protocolFactory(new TBinaryProtocol.Factory());

        TNonblockingServer server = new TNonblockingServer(serverArgs);

        log.info("start up user service at {}", port);
        server.serve();

        log.info("user service stop service...");

    }
}
