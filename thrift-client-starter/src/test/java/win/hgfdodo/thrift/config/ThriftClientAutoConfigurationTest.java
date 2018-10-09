//package win.hgfdodo.thrift.config;
//
//
//import org.apache.thrift.TException;
//import org.apache.thrift.TServiceClient;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import win.hgfdodo.course.user.UserService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ThriftConfig.class, ThriftClientAutoConfiguration.class})
//public class ThriftClientAutoConfigurationTest {
//
//    @Autowired
//    ThriftConfig thriftConfig;
//
//    @Autowired
//    TServiceClient client;
//
//    @Test
//    public void autoConfigTest(){
//        Assert.assertNotNull( thriftConfig);
//    }
//
//    @Test
//    public void getThriftClient() throws TException {
//        Assert.assertNotNull( client);
//        UserService.Client userServiceClient = (UserService.Client) client;
//        System.out.println(userServiceClient.getUserById(1));
//    }
//}