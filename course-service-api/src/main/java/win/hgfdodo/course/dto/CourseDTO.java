package win.hgfdodo.course.dto;

import win.hgfdodo.course.user.dto.TeacherDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class CourseDTO implements Serializable {
    public int id;
    public String title;
    public String description;
    public int students;
    public String address;
    public double price;
    public int stars;
    public TeacherDTO teacher;
    public Date starttime;

    public CourseDTO() {
    }

    public CourseDTO(int id, String title, String description, int students, String address, double price, int stars, TeacherDTO teacher, Date starttime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.students = students;
        this.address = address;
        this.price = price;
        this.stars = stars;
        this.teacher = teacher;
        this.starttime = starttime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", students=" + students +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", stars=" + stars +
                ", teacher=" + teacher +
                ", starttime=" + starttime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDTO courseDTO = (CourseDTO) o;
        return id == courseDTO.id &&
                students == courseDTO.students &&
                Double.compare(courseDTO.price, price) == 0 &&
                stars == courseDTO.stars &&
                Objects.equals(title, courseDTO.title) &&
                Objects.equals(description, courseDTO.description) &&
                Objects.equals(address, courseDTO.address) &&
                Objects.equals(teacher, courseDTO.teacher) &&
                Objects.equals(starttime, courseDTO.starttime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, students, address, price, stars, teacher, starttime);
    }
}
