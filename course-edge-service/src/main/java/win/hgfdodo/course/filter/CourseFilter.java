package win.hgfdodo.course.filter;

import org.springframework.stereotype.Component;
import win.hgfdodo.course.user.dto.UserDTO;
import win.hgfdodo.user.filter.LoginFilter;
import win.hgfdodo.user.filter.config.UserFilterConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CourseFilter extends LoginFilter {

    public CourseFilter(UserFilterConfig userFilterConfig) {
        super(userFilterConfig);
    }

    @Override
    protected void afterLogin(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {
        request.setAttribute("user", userDTO);
    }

}
