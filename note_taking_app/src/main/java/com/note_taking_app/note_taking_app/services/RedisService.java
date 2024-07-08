package com.note_taking_app.note_taking_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private static final long DEFAULT_TIMEOUT = 10;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MINUTES;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT);
    }


    public String getValue(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}
