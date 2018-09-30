package win.hgfdodo.course.response;

public class TokenResponse extends Response {
    private String token;

    public TokenResponse(String token) {
        super(ResponseType.LOGIN_SUCCESS);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
