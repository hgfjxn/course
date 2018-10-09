package win.hgfdodo.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import win.hgfdodo.course.service.CourseService;

@Controller("/course")
public class CourseController {

    @Reference()
    private CourseService courseService;
}
