package com.xuhaoming.hamidemo.controller;

import com.xuhaoming.hamidemo.pojo.RedisCacheVO;
import io.github.dovemy.hami.redis.RedisCacheExpireConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author xuhaoming
 * @date 2021/10/3
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("set")
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("get")
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("cache")
    @Cacheable(key = "'getByRedisCache'", cacheNames = "MINUTE_20")
    //@Cacheable(key = "'getByRedisCache'", cacheNames = RedisCacheExpireConfig.HOUR_1)
    public RedisCacheVO getByRedisCache () {
        RedisCacheVO vo = new RedisCacheVO();
        vo.setId(1L);
        vo.setName("name");
        vo.setCreateTime(new Date());
        return vo;
    }

}
