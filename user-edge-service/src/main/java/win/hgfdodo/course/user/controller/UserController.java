package win.hgfdodo.course.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signin")
    public void signIn(@RequestParam("username") String username,
                       @RequestParam("password") String password){
        log.info("{} sigin ", username);

    }
}
