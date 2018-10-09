package win.hgfdodo.course.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import win.hgfdodo.course.dto.CourseDTO;
import win.hgfdodo.course.mapper.CourseMapper;
import win.hgfdodo.course.user.User;
import win.hgfdodo.course.user.UserService;
import win.hgfdodo.course.user.dto.TeacherDTO;

import java.util.List;

/**
 * Assume parameter is not null
 */

@Service(version = "${course.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}")
public class CourseServiceimpl implements CourseService {

    private final static Logger log = LoggerFactory.getLogger(CourseServiceimpl.class);

    private final CourseMapper courseMapper;
    private final UserService.Iface client;

    public CourseServiceimpl(CourseMapper courseMapper, UserService.Iface client) {
        this.courseMapper = courseMapper;
        this.client = client;
    }


    @Override
    public CourseDTO getCourseById(int id) {
        CourseDTO courseDTO = courseMapper.getCourseById(id);
        if (courseDTO != null) {
            TeacherDTO teacherDTO = getCourseTeacher(courseDTO.getId());
            courseDTO.setTeacher(teacherDTO);
        }
        return courseDTO;
    }

    @Override
    public CourseDTO getCourseByTitle(String title) {
        CourseDTO courseDTO = courseMapper.getCourseByTitle(title);
        if (courseDTO != null) {
            TeacherDTO teacherDTO = getCourseTeacher(courseDTO.getId());
            courseDTO.setTeacher(teacherDTO);
        }
        return courseDTO;
    }

    @Override
    public List<CourseDTO> getCourses() {
        List<CourseDTO> courseDTOS = courseMapper.getCourses();
        if (courseDTOS != null) {
            for (CourseDTO courseDTO : courseDTOS) {
                if (courseDTO != null) {
                    TeacherDTO teacherDTO = getCourseTeacher(courseDTO.getId());
                    courseDTO.setTeacher(teacherDTO);
                }
            }
        }
        return courseDTOS;
    }

    @Override
    public TeacherDTO getCourseTeacher(int courseId) {
        int teacherId = courseMapper.getCourseTeacherId(courseId);
        try {
            User teacher = client.getTeacherById(teacherId);
            TeacherDTO teacherDTO = TeacherDTO.fromUser(teacher);
            return teacherDTO;
        } catch (TException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addCourse(CourseDTO courseDTO) {
        boolean flag = courseMapper.addCourse(courseDTO);
        flag = courseMapper.addCourseTeacher(courseDTO.getTeacher().getId(), courseDTO.getId());
        return flag;
    }

    @Override
    public boolean deleteCourse(int id) {
        boolean flag = courseMapper.deleteCourseTeacher(id);
        flag = courseMapper.deleteCourse(id);
        return flag;
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO) {
        if (courseDTO.getId() == 0) {
            throw new RuntimeException("update course do not support batch update, please specific update course id!");
        }
        CourseDTO origin = getCourseById(courseDTO.getId());
        if (origin != null) {
            courseMapper.updateCourse(courseDTO);
            if (origin.getTeacher().getId() != courseDTO.getTeacher().getId()) {
                courseMapper.updateCourseTeacher(courseDTO.getTeacher().getId(), courseDTO.getId());
            }
        } else {
            throw new RuntimeException("course do not exists! courseId = " + courseDTO.getId());
        }

        return origin;
    }
}
