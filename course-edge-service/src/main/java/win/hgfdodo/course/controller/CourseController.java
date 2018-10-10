package win.hgfdodo.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import win.hgfdodo.course.dto.CourseDTO;
import win.hgfdodo.course.service.CourseService;
import win.hgfdodo.course.user.dto.TeacherDTO;
import win.hgfdodo.course.user.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CourseController {

    private final static Logger log = LoggerFactory.getLogger(CourseController.class);

//    @Reference(version = "${course.service.version}",
//            application = "${dubbo.application.id}",
//            registry = "${dubbo.registry.address}")
    @Reference(version = "1.0.0")
    private CourseService courseService;

    @PostMapping("/course")
    public boolean addCourse(@RequestBody CourseDTO courseDTO, HttpServletRequest request) {
        log.debug("Request to add course: {}", courseDTO);

        //默认课程教师为当前的添加者
        if (courseDTO.getTeacher() == null) {
            UserDTO userDTO = (UserDTO) request.getAttribute("user");
            TeacherDTO teacherDTO = new TeacherDTO(userDTO);
            courseDTO.setTeacher(teacherDTO);
        }
        return courseService.addCourse(courseDTO);
    }

    @GetMapping(value = "/course/all", produces = "application/json")
    @ResponseBody
    public List<CourseDTO> getCourses(HttpServletRequest request) {
        log.debug("Request to get courses");
        UserDTO userDTO = (UserDTO) request.getAttribute("user");
        log.info("login user: {}", userDTO);

        return courseService.getCourses();
    }

    @GetMapping("/course/{id}")
    public CourseDTO getCourse(@PathVariable int id) {
        log.debug("Request to get course by id {}", id);
        return courseService.getCourseById(id);
    }

    @GetMapping("/course")
    public CourseDTO getCourseByTitle(@RequestParam("title") String title) {
        log.debug("Request to get course by title {}", title);
        return courseService.getCourseByTitle(title);
    }

    @PutMapping("/course")
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO) {
        log.debug("Request to update course to", courseDTO);
        return courseService.updateCourse(courseDTO);
    }

    @DeleteMapping("/course/{id}")
    public boolean deleteCourse(@PathVariable int id) {
        log.debug("Request to delete course: {}", id);
        return courseService.deleteCourse(id);
    }

}
