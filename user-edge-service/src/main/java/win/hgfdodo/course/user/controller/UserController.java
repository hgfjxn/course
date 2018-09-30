package win.hgfdodo.course.user.controller;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import win.hgfdodo.course.message.MessageService;
import win.hgfdodo.course.response.ExceptionResponse;
import win.hgfdodo.course.response.Response;
import win.hgfdodo.course.response.ResponseType;
import win.hgfdodo.course.response.TokenResponse;
import win.hgfdodo.course.user.User;
import win.hgfdodo.course.user.UserService;
import win.hgfdodo.course.user.constant.Constants;
import win.hgfdodo.course.user.dto.UserDTO;
import win.hgfdodo.course.user.redis.RedisClient;
import win.hgfdodo.course.utils.PasswordUtils;
import win.hgfdodo.course.utils.TokenUtils;


@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService.Client userServiceClient;

    private final MessageService.Client messageServiceClient;

    private final RedisClient redisClient;

    public UserController(UserService.Client userServiceClient, MessageService.Client messageServiceClient, RedisClient redisClient) {
        this.userServiceClient = userServiceClient;
        this.messageServiceClient = messageServiceClient;
        this.redisClient = redisClient;
    }

    @PostMapping("/signin")
    public Response signIn(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        log.debug("{} sigin ", username);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Response.of(ResponseType.USERNAME_OR_PASSWORD_MISSING);
        }

        User user = null;
        try {
            user = userServiceClient.getUserByName(username);
        } catch (TException e) {
            log.error("user service exception ", e);
            return new ExceptionResponse(e);
        }
        if (PasswordUtils.matched(password, user.getPassword())) {
            //生成token
            String token = TokenUtils.token(Constants.TOKEN_LENGTH);
            //缓存
            redisClient.set(token, UserDTO.fromUser(user));

            return new TokenResponse(token);
        } else {
            return Response.of(ResponseType.PASSWORD_INCORRECT);
        }
    }

    @PostMapping("/signup")
    public Response signUp(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "phone", required = false) String phone,
                           @RequestParam("verifyCode") String verifyCode) {
        log.debug("sign up {}: {}, {}, {}, {}", username, password, email, phone, verifyCode);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Response.of(ResponseType.USERNAME_OR_PASSWORD_MISSING);
        }
        if (StringUtils.isEmpty(verifyCode)) {
            return Response.of(ResponseType.VERIFYCODE_IS_MISSING_OR_OUT_OF_TIME);
        } else {
            String verifyCodeStored = (String) redisClient.get(username);
            if (verifyCode.equals(verifyCodeStored)) {
                return Response.of(ResponseType.SIGNUP_SUCCESS);
            } else {
                return Response.of(ResponseType.VERIFYCODE_IS_MISSING_OR_OUT_OF_TIME);
            }
        }
    }

    @PostMapping("/verifycode")
    public Response verifyCode(@RequestParam("username") String username,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "phone", required = false) String phone) {
        log.debug("Request to get verify code of user {} by email: {}, phone: {}", username, email, phone);
        if (StringUtils.isEmpty(email) && StringUtils.isEmpty(phone)) {
            return Response.of(ResponseType.EMAIL_PHONE_BOTH_EMPTY);
        } else {
            //check username used by someone ?
            User user = null;
            try {
                user = userServiceClient.getUserByName(username);
            } catch (TException e) {
                log.error("user thrift service exception: ", e);
                new ExceptionResponse(e);
            }
            if (user != null) {
                return Response.of(ResponseType.USERNAME_EXISTS);
            }

            String verifyCode = TokenUtils.veriyCode(Constants.VERIFY_CODE_LENGTH);
            String content = String.format("Dear %s,\r\n\t ypur VerifyCode is %, please keep is secret", username, verifyCode);
            try {
                if (StringUtils.isEmpty(email)) {
                    messageServiceClient.sendEmailMessage(email, content);
                } else {
                    messageServiceClient.sendMobileMessage(phone, content);
                }
                redisClient.set(username, verifyCode);
                return Response.of(ResponseType.VERIFYCODE_SEND_SUCCESS);
            } catch (TException e) {
                log.error("message service exception: ", e);
                return new ExceptionResponse(e);
            }
        }
    }
}
