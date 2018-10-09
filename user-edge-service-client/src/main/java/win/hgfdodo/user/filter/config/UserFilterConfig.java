package win.hgfdodo.user.filter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "user.filter")
public class UserFilterConfig {
    private String userEdgeService;
    private String loginPage;
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

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    @Override
    public String toString() {
        return "UserFilterConfig{" +
                "userEdgeService='" + userEdgeService + '\'' +
                ", loginPage='" + loginPage + '\'' +
                ", authentication='" + authentication + '\'' +
                '}';
    }
}
