package win.hgfdodo.course.utils;

import org.junit.Test;

public class PasswordUtilsTest {

    @Test
    public void matched() {
        String encodeed = "$2a$10$QSuOxmw5mtjy/1KILHJXlOaqe1gU1qfDmZ5KEmH1S.9u83Hlib216";
        String raw = "jxngood";
        System.out.println( PasswordUtils.matched(raw, encodeed) == true);
    }
}