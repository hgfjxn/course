package win.hgfdodo.course.user.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {

    @Autowired
    private RedisTemplate redisTemplate;

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
    }

    public void set(String key, Object val, int timeout) {
        redisTemplate.opsForValue().set(key, val, timeout, TimeUnit.SECONDS);
    }

    public void expire(String key, int timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
}
