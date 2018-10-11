package win.hgfdodo.course.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import win.hgfdodo.course.user.dto.UserDTO;
import win.hgfdodo.user.filter.LoginFilter;
import win.hgfdodo.user.filter.config.UserFilterConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CourseFilter extends LoginFilter {

    private Logger log = LoggerFactory.getLogger(CourseFilter.class);

    public CourseFilter(UserFilterConfig userFilterConfig) {
        super(userFilterConfig);
        log.info("user filter config: {}" + userFilterConfig);
    }

    @Override
    protected void afterLogin(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {
        request.setAttribute("user", userDTO);
    }

}
