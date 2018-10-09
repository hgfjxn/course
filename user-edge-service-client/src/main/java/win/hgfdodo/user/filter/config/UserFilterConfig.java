package win.hgfdodo.user.filter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "user.filter")
public class UserFilterConfig {
    private String userEdgeService;
    private String login;
    private String authentication;

    public String getUserEdgeService() {
        return userEdgeService;
    }

    public void setUserEdgeService(String userEdgeService) {
        this.userEdgeService = userEdgeService;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "UserFilterConfig{" +
                "userEdgeService='" + userEdgeService + '\'' +
                ", login='" + login + '\'' +
                ", authentication='" + authentication + '\'' +
                '}';
    }
}
