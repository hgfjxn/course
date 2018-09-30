package win.hgfdodo.course.utils;

import win.hgfdodo.course.utils.password.BCryptPasswordEncoder;

public class PasswordUtils {
    private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static boolean matched(String rawPasswrod, String encodedPassword){
        return encoder.matches(rawPasswrod, encodedPassword);
    }

    public static String encodePassword(String rawPassowrd){
        return encoder.encode(rawPassowrd);
    }
}
