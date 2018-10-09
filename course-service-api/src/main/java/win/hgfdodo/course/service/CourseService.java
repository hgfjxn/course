package win.hgfdodo.course.service;

import win.hgfdodo.course.dto.CourseDTO;
import win.hgfdodo.course.user.dto.TeacherDTO;

import java.util.List;

public interface CourseService {

    public CourseDTO getCourseById(int id);

    public CourseDTO getCourseByTitle(String title);

    public List<CourseDTO> getCourses();

    public TeacherDTO getCourseTeacher(int courseId);

    public boolean addCourse(CourseDTO courseDTO);

    public boolean deleteCourse(int id);

    /**
     * return origin courseDTO
     * @param courseDTO
     * @return
     */
    public CourseDTO updateCourse(CourseDTO courseDTO);
}
