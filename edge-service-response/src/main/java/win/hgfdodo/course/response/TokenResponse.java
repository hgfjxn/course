package win.hgfdodo.course.response;

import java.util.HashMap;
import java.util.Map;

public class TokenResponse extends Response {

    public TokenResponse(String token) {
        super(ResponseType.LOGIN_SUCCESS, token);
    }
}
