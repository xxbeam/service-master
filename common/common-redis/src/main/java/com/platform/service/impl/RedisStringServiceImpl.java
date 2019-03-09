package com.platform.service.impl;

import com.platform.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("redisStringService")
public class RedisStringServiceImpl implements RedisService<String> {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void setForValue(String key, String value, long mills, TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(key,value,mills,timeUnit);
    }

    public String getForValue(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void removeKey(String key){
        stringRedisTemplate.delete(key);
    }

    @Override
    public void setForSet(String key, String value) {
        stringRedisTemplate.opsForSet().add(key,value);
    }

    @Override
    public Set<String> getForSet(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

}
