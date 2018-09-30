package win.hgfdodo.course.utils;

import java.util.Random;

public class TokenUtils {

    private final static Random random = new Random();

    public final static String token(int length) {
        return randomCode(Consts.TOKEN_CHARACTERS, length);
    }

    public final static String veriyCode(int length) {
        return randomCode(Consts.VERiFY_CHARACTERS, length);
    }

    private static String randomCode(String characters, int length) {
        StringBuffer sb = new StringBuffer();
        int l = characters.length();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(l)));
        }

        return sb.toString();
    }

}
