package win.hgfdodo.course.user.service;

import org.apache.thrift.TException;
import win.hgfdodo.course.user.User;
import win.hgfdodo.course.user.UserService;

public class UserServiceImpl implements UserService.Iface {
    @Override
    public void signUp(User user) throws TException {

    }

    @Override
    public void signIn(String username, String password) throws TException {

    }

    @Override
    public User getUserById(int id) throws TException {
        return null;
    }

    @Override
    public User getUserByName(String username) throws TException {
        return null;
    }
}
