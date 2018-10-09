package win.hgfdodo.course.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import win.hgfdodo.course.user.User;

@Mapper
public interface UserMapper {

    @Select("select id, username, password, realname, phone, email from pe_user where id=#{id}")
    User getUserById(@Param("id") int id);

    @Select("select id, username, password, realname, phone, email from pe_user where username=#{username}")
    User getUserByName(@Param("username") String username);

    @Insert("insert into " +
            "pe_user(username, password, realname, phone, email) " +
            "values" +
            "(#{user.username}, #{user.password}, #{user.realname}, #{user.phone}, #{user.email})")
    void signUp(@Param("user") User user);

    @Select("select id, username, password, realname, phone, email, introduction, stars from pe_user where id=#{id}")
    User getTeacherById(@Param("id") int id);
}
