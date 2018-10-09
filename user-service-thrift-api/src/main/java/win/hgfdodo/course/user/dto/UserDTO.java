package win.hgfdodo.course.user.dto;

import win.hgfdodo.course.user.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    public int id;
    public java.lang.String username;
    public java.lang.String password;
    public java.lang.String realname;
    public java.lang.String email;
    public java.lang.String phone;

    public UserDTO() {
    }

    public UserDTO(int id, String username, String password, String realname, String email, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.email = email;
        this.phone = phone;
    }

    public UserDTO(User user) {
        this(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRealname(),
                user.getEmail(),
                user.getPhone());
    }

    public static UserDTO fromUser(User user){
        UserDTO userDto = new UserDTO(user);
        return userDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
