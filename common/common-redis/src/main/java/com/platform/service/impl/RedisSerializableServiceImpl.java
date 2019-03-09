package com.platform.service.impl;

import com.platform.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("redisSerializableService")
public class RedisSerializableServiceImpl implements RedisService<Serializable> {

    @Autowired
    RedisTemplate<String, Serializable> redisCacheTemplate;

    public void setForValue(String key, Serializable value, long mills, TimeUnit timeUnit){
        redisCacheTemplate.opsForValue().set(key,value,mills,timeUnit);
    }

    public Serializable getForValue(String key){
        return redisCacheTemplate.opsForValue().get(key);
    }

    public void removeKey(String key){
        redisCacheTemplate.delete(key);
    }

    public void setForSet(String key, Serializable value){
        redisCacheTemplate.opsForSet().add(key,value);
    }

    public Set<Serializable> getForSet(String key){
        Set<Serializable> set =  redisCacheTemplate.opsForSet().members(key);
        return set;
    }

}
