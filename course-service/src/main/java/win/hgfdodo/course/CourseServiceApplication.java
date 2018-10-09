package win.hgfdodo.course;

import org.apache.thrift.TServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import win.hgfdodo.course.dto.CourseDTO;
import win.hgfdodo.course.mapper.CourseMapper;
import win.hgfdodo.course.service.CourseService;
import win.hgfdodo.course.service.CourseServiceimpl;
import win.hgfdodo.course.user.UserService;
import win.hgfdodo.course.user.dto.TeacherDTO;
import win.hgfdodo.thrift.config.ThriftConfig;

import java.util.List;

@SpringBootApplication
public class CourseServiceApplication implements CommandLineRunner {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    ThriftConfig thriftConfig;

    @Bean
    public UserService.Iface getUserService(TServiceClient serviceClient){
        return (UserService.Iface) serviceClient;
    }

    @Autowired
    UserService.Iface userServiceClient;

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(thriftConfig);

        CourseService courseService = new CourseServiceimpl(courseMapper, userServiceClient);
        CourseDTO courseDTO = courseService.getCourseById(1);
        System.out.println(courseDTO);
        List<CourseDTO> courseDTOList = courseService.getCourses();
        System.out.println(courseDTOList);
        TeacherDTO teacherDTO = courseService.getCourseTeacher(1);
        courseDTO.setAddress("湖北省");
        courseDTO.setStars(5);
        courseDTO.setTitle("a");
        CourseDTO origin = courseService.updateCourse(courseDTO);
        System.out.println(origin);
        courseDTO = courseService.getCourseByTitle("a");
        System.out.println(courseDTO);
        courseService.deleteCourse(1);
        courseDTOList = courseService.getCourses();
        System.out.println(courseDTOList);
    }
}
