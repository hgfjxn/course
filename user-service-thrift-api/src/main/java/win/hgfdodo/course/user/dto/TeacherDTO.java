package win.hgfdodo.course.user.dto;

import org.springframework.beans.BeanUtils;
import win.hgfdodo.course.user.User;

public class TeacherDTO extends UserDTO {
    private String introduction;
    private int stars;

    public TeacherDTO() {
    }

    public TeacherDTO(String introduction, int stars) {
        this.introduction = introduction;
        this.stars = stars;
    }

    public TeacherDTO(int id, String username, String password, String realname, String email, String phone, String introduction, int stars) {
        super(id, username, password, realname, email, phone);
        this.introduction = introduction;
        this.stars = stars;
    }

    public TeacherDTO(User user) {
        super(user);
        this.introduction = user.introduction;
        this.stars = user.getStars();
    }

    public TeacherDTO(UserDTO userDTO){
        super(userDTO.id, userDTO.username, userDTO.password, userDTO.realname, userDTO.email, userDTO.phone);
    }

    public static TeacherDTO fromUser(User user){
        TeacherDTO teacherDTO = new TeacherDTO(user);
        return teacherDTO;
    }

    public static User toUser(TeacherDTO teacherDTO){
        User user = new User();
        BeanUtils.copyProperties(teacherDTO, user);
        return user;
    }

    public static UserDTO toUserDto(TeacherDTO teacherDTO){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(teacherDTO, userDTO);
        return userDTO;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "introduction='" + introduction + '\'' +
                ", stars=" + stars +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
