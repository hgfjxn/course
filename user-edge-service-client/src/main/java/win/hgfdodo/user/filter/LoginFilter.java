package win.hgfdodo.user.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import win.hgfdodo.course.user.dto.UserDTO;
import win.hgfdodo.user.filter.config.UserFilterConfig;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@EnableConfigurationProperties(UserFilterConfig.class)
@WebFilter
public abstract class LoginFilter implements Filter {

    private final UserFilterConfig userFilterConfig;

    public LoginFilter(UserFilterConfig userFilterConfig) {
        this.userFilterConfig = userFilterConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equalsIgnoreCase("token")) {
                        token = cookie.getValue();
                    }
                }
            }
        }

        UserDTO userDTO = null;
        if (!StringUtils.isEmpty(token)) {
            userDTO = getUserInfo(token);
        }
        if (userDTO == null) {
            response.sendRedirect(userFilterConfig.getUserEdgeService() + userFilterConfig.getLogin());
            return;
        }

        login(request, response, userDTO);

        filterChain.doFilter(request, response);

    }

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO);

    private UserDTO getUserInfo(String token) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet post = new HttpGet(userFilterConfig.getUserEdgeService() + userFilterConfig.getAuthentication());
        post.addHeader("token", token);
        try {
            HttpResponse response = client.execute(post);
            InputStream in = response.getEntity().getContent();
            ObjectMapper objectMapper = new ObjectMapper();
            UserDTO userDTO = objectMapper.readValue(in, UserDTO.class);
            return userDTO;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void destroy() {

    }
}
