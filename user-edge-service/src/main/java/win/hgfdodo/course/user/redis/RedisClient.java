package win.hgfdodo.course.user.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisClient(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

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
