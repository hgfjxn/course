package win.hgfdodo.course.mapper;

import org.apache.ibatis.annotations.*;
import win.hgfdodo.course.dto.CourseDTO;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("select id, title, description, students, stars, address, price,starttime from pe_course where id=#{id}")
    public CourseDTO getCourseById(@Param("id") int id);

    @Select("select id, title, description, students, stars, address, price,starttime from pe_course where title=#{title}")
    public CourseDTO getCourseByTitle(@Param("title") String title);

    @Insert("insert into pe_course(title, description, " +
            "students, stars, address, price, starttime) " +
            "values " +
            "(#{course.title}, #{course.description}, " +
            "#{course.students}, #{course.stars}, " +
            "#{course.address}, #{course.price}, " +
            "#{course.starttime})")
    public boolean addCourse(@Param("course") CourseDTO courseDTO);

    @Insert("insert into pr_user_course(user_id, course_id) values(#{userId}, #{courseId})")
    public boolean addCourseTeacher(@Param("userId") int userId, @Param("courseId") int courseId);

    @Delete("delete from pe_course where id=#{id}")
    public boolean deleteCourse(@Param("id") int id);

    @Delete("delete from pr_user_course where course_id=#{courseId}")
    public boolean deleteCourseTeacher(@Param("courseId") int courseId);

    @Update("update pe_course set title=#{course.title}," +
            " description=#{course.description}," +
            " students=#{course.students}, stars=#{course.stars}," +
            " address=#{course.address}, price=#{course.price}," +
            "starttime=#{course.starttime} where id = #{course.id}")
    public void updateCourse(@Param("course") CourseDTO courseDTO);

    @Update("update pr_user_course set user_id=#{userId} where course_id=#{courseId}")
    public void updateCourseTeacher(@Param("userId") int userId, @Param("courseId") int courseId);

    @Select("select id, title, description, students, stars, address, price,starttime from pe_course")
    public List<CourseDTO> getCourses();

    @Select("select user_id from pr_user_course where course_id=#{courseId}")
    public int getCourseTeacherId(@Param("courseId") int courseId);
}
