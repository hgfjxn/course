package win.hgfdodo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import win.hgfdodo.course.dto.CourseDTO;
import win.hgfdodo.course.mapper.CourseMapper;
import win.hgfdodo.course.service.CourseService;
import win.hgfdodo.course.user.UserService;

import java.util.List;

@SpringBootApplication
public class CourseServiceApplication implements CommandLineRunner {

    @Value("${course.service.version}")
    private String version;
    @Value("${dubbo.application.id}")
    private String application;
    @Value("${dubbo.protocol.id}")
    private String protocol;
    @Value("${dubbo.registry.id}")
    private String registry;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    private UserService.Iface client;

    @Autowired
    CourseService courseService;

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        System.out.println(courseMapper);
        System.out.println(client);

        List<CourseDTO> courseDTOList = courseService.getCourses();
        System.out.println(courseDTOList);
    }
}
