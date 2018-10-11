package win.hgfdodo.course.user.service;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;
import win.hgfdodo.course.user.User;
import win.hgfdodo.course.user.UserService;
import win.hgfdodo.course.user.mapper.UserMapper;
import win.hgfdodo.course.utils.PasswordUtils;

@Service
public class UserServiceImpl implements UserService.Iface {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void signUp(User user) throws TException {
        userMapper.signUp(user);
    }

    @Override
    public User getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByName(String username) throws TException {
        return userMapper.getUserByName(username);
    }

    @Override
    public User getTeacherById(int id) throws TException {
        return userMapper.getTeacherById(id);
    }

}
