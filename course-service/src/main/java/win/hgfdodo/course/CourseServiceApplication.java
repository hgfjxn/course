package win.hgfdodo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import win.hgfdodo.course.dto.CourseDTO;
import win.hgfdodo.course.service.CourseService;
import win.hgfdodo.course.user.UserService;
import win.hgfdodo.course.user.dto.TeacherDTO;
import win.hgfdodo.thrift.config.ThriftConfig;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class CourseServiceApplication implements CommandLineRunner {
    @Autowired
    CourseService courseService;

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(courseService.getCourseTeacher(1));
    }
}
