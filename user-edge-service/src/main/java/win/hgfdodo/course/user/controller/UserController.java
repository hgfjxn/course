package win.hgfdodo.course.user.controller;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "/signin", produces = "application/json")
    @ResponseBody
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

    @PostMapping(value = "/signup", produces = "application/json")
    @ResponseBody
    public Response signUp(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("realname") String realname,
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
                String encodePassword = PasswordUtils.encodePassword(password);
                User user = new User();
                user.setUsername(username);
                user.setPassword(encodePassword);
                user.setRealname(realname);
                user.setEmail(email);
                user.setPhone(phone);
                try {
                    userServiceClient.signUp(user);
                    return Response.of(ResponseType.SIGNUP_SUCCESS);
                } catch (TException e) {
                    return new ExceptionResponse(e);
                }
            } else {
                return Response.of(ResponseType.VERIFYCODE_IS_MISSING_OR_OUT_OF_TIME);
            }
        }
    }

    @PostMapping(value = "/verifycode", produces = "application/json")
    @ResponseBody
    public Response verifyCode(@RequestParam("username") String username,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "phone", required = false) String phone) {
        log.debug("Request to get verify code of user {} by email: {}, phone: {}", username, email, phone);
        if (StringUtils.isEmpty(email) && StringUtils.isEmpty(phone)) {
            return Response.of(ResponseType.EMAIL_PHONE_BOTH_EMPTY);
        } else {
            //check username used by someone ?
//            User user = null;
//            try {
//                user = userServiceClient.getUserByName(username);
//            } catch (TException e) {
//                log.warn("user thrift service exception: ", e);
//            }
//            if (user != null) {
//                return Response.of(ResponseType.USERNAME_EXISTS);
//            }

            String verifyCode = TokenUtils.veriyCode(Constants.VERIFY_CODE_LENGTH);
            String content = String.format("Dear %s,\r\n\t your VerifyCode is %s, please keep is secret", username, verifyCode);
            boolean send = false;
            try {
                if (!StringUtils.isEmpty(email)) {
                    send = messageServiceClient.sendEmailMessage(email, content);
                } else {
                    send = messageServiceClient.sendMobileMessage(phone, content);
                }
                if (send) {
                    redisClient.set(username, verifyCode);
                    return Response.of(ResponseType.VERIFYCODE_SEND_SUCCESS);
                } else {
                    return Response.of(ResponseType.MESSAGE_SERVICE_EXCEPTION);
                }
            } catch (TException e) {
                log.error("message service exception: ", e);
                return new ExceptionResponse(e);
            }
        }
    }

    @PostMapping(value = "/authentication", produces = "application/json")
    @ResponseBody
    public UserDTO authentication(@RequestParam("token")String token){
        log.debug("Request to get user information by token: {}", token);
        if(!StringUtils.isEmpty(token)){
            UserDTO userDTO = (UserDTO) redisClient.get(token);
            return userDTO;
        }else {
            return null;
        }
    }
}
